package br.com.rembert.miniautorizador.service;

import br.com.rembert.miniautorizador.exception.InsufficientFundsException;
import br.com.rembert.miniautorizador.exception.InvalidPasswordException;
import br.com.rembert.miniautorizador.exception.NonExistentCardException;
import br.com.rembert.miniautorizador.model.adapter.TransactionAdapter;
import br.com.rembert.miniautorizador.model.dto.TransactionDto;
import br.com.rembert.miniautorizador.model.entity.Card;
import br.com.rembert.miniautorizador.repository.CardRepository;
import br.com.rembert.miniautorizador.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Log4j2
@Service
public class TransactionService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public String executeTransaction(TransactionDto transactionDto) {
        Card card = this.cardRepository.findByCardNumber(transactionDto.getNumeroCartao()).orElseThrow(NonExistentCardException::new);
        this.validPassword(card, transactionDto);
        this.balanceDebit(card, transactionDto);
        this.transactionRepository.saveAndFlush(TransactionAdapter.dtoToEntity(transactionDto, card));
        return HttpStatus.OK.getReasonPhrase();
    }

    void validPassword(Card card, TransactionDto transactionDto) {
        if (!card.getPassword().equals(transactionDto.getSenhaCartao())) throw new InvalidPasswordException();
    }

    @Transactional
    void balanceDebit(Card card, TransactionDto transactionDto) {
        if (card.getBalance().subtract(transactionDto.getValor()).compareTo(BigDecimal.ZERO) < 0)
            throw new InsufficientFundsException();
        card.setBalance(card.getBalance().subtract(transactionDto.getValor()));
        this.cardRepository.saveAndFlush(card);
    }
}
