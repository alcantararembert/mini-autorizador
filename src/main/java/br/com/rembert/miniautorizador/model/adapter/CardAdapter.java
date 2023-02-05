package br.com.rembert.miniautorizador.model.adapter;

import br.com.rembert.miniautorizador.model.dto.CardDto;
import br.com.rembert.miniautorizador.model.entity.Card;

import java.math.BigDecimal;

public class CardAdapter {

    public static Card dtoToEntity(CardDto cardDto) {
        return Card.builder()
                .cardNumber(cardDto.getNumeroCartao())
                .password(cardDto.getSenha())
                .balance(BigDecimal.valueOf(500)).build();
    }
}
