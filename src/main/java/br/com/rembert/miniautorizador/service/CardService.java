package br.com.rembert.miniautorizador.service;

import br.com.rembert.miniautorizador.exception.CardNotFoundException;
import br.com.rembert.miniautorizador.exception.CardNumberAlreadyDefinedException;
import br.com.rembert.miniautorizador.model.adapter.CardAdapter;
import br.com.rembert.miniautorizador.model.dto.CardDto;
import br.com.rembert.miniautorizador.model.entity.Card;
import br.com.rembert.miniautorizador.repository.CardRepository;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Log4j2
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public CardDto createCard(CardDto cardDto) {
        this.cardNumberAlreadyExists(cardDto);
        this.cardRepository.saveAndFlush(CardAdapter.dtoToEntity(cardDto));
        return cardDto;
    }

    private void cardNumberAlreadyExists(CardDto cardDto) {
        this.cardRepository.findByCardNumber(cardDto.getNumeroCartao()).ifPresent(e -> {
            log.error("Card number already defined - {}", cardDto.getNumeroCartao());
            /* Change HttpStatus to 409, means there are conflict from send information and the databases records*/
            throw new CardNumberAlreadyDefinedException(new Gson().toJson(cardDto));
        });
    }

    public BigDecimal getBalance(String cardNumber) {
        Card card = this.cardRepository.findByCardNumber(cardNumber).orElseThrow(CardNotFoundException::new);
        return card.getBalance();
    }
}
