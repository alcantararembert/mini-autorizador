package br.com.rembert.miniautorizador.model.adapter;

import br.com.rembert.miniautorizador.model.dto.TransactionDto;
import br.com.rembert.miniautorizador.model.entity.Card;
import br.com.rembert.miniautorizador.model.entity.Transaction;

public class TransactionAdapter {

    public static Transaction dtoToEntity(TransactionDto transaction, Card card) {
        return Transaction.builder()
                .cardNumber(transaction.getNumeroCartao())
                .password(transaction.getSenhaCartao())
                .value(transaction.getValor())
                .card(card).build();
    }
}
