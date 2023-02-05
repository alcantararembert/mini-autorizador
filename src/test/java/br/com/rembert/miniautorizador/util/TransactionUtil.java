package br.com.rembert.miniautorizador.util;

import br.com.rembert.miniautorizador.model.dto.TransactionDto;
import java.math.BigDecimal;

public class TransactionUtil {

    public static TransactionDto getTransaction() {
        return TransactionDto.builder()
                .numeroCartao("65498730256345012")
                .senhaCartao("1234")
                .valor(BigDecimal.valueOf(501)).build();
    }

    public static TransactionDto getTransactionWithInvalidPassword() {
        return TransactionDto.builder()
                .numeroCartao("65498730256345013")
                .senhaCartao("1")
                .valor(BigDecimal.ONE).build();
    }

    public static TransactionDto getTransactionWithNonExistentCard() {
        return TransactionDto.builder()
                .numeroCartao("65498730256345014")
                .senhaCartao("1234")
                .valor(BigDecimal.ONE).build();
    }
}
