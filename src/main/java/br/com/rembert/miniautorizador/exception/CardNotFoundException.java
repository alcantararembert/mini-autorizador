package br.com.rembert.miniautorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException {

    private final static String CARD_NOT_FOUND = "CARTAO_NAO_ENCONTRADO";

    public CardNotFoundException() {
        super(CARD_NOT_FOUND);
    }
}
