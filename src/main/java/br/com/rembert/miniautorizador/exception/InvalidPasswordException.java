package br.com.rembert.miniautorizador.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidPasswordException extends RuntimeException {

    private final static String INVALID_PASSWORD = "SENHA_INVALIDA";

    public InvalidPasswordException() {
        super(INVALID_PASSWORD);
    }

}
