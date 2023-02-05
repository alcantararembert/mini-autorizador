package br.com.rembert.miniautorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InsufficientFundsException extends RuntimeException {

    private final static String INSUFFICIENT_FUNDS = "SALDO_INSUFICIENTE";

    public InsufficientFundsException() {
        super(INSUFFICIENT_FUNDS);
    }

}
