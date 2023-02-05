package br.com.rembert.miniautorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CardNumberAlreadyDefinedException extends RuntimeException {

    public CardNumberAlreadyDefinedException(String message) {
        super(message);
    }

}
