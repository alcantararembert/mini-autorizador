package br.com.rembert.miniautorizador.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NonExistentCardException extends RuntimeException {

    private final static String NONEXISTENT_CARD = "CARTAO_INEXISTENTE";

    public NonExistentCardException() {
        super(NONEXISTENT_CARD);
    }

}
