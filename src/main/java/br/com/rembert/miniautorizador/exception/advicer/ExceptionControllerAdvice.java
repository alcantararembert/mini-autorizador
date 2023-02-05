package br.com.rembert.miniautorizador.exception.advicer;

import br.com.rembert.miniautorizador.exception.*;
import br.com.rembert.miniautorizador.model.dto.ExceptionDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@Log4j2
@ControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public static ExceptionDTO handleException(RuntimeException e) {
        log.error("Internal Server Error: {}", e.getMessage());
        return new ExceptionDTO(INTERNAL_SERVER_ERROR.value(), "Erro no sistema, tente mais tarde.");
    }

    @ResponseBody
    @ResponseStatus(CONFLICT)
    @ExceptionHandler(CardNumberAlreadyDefinedException.class)
    public static ExceptionDTO handleConflictException(RuntimeException e) {
        log.error("Conflict: {}", e.getMessage());
        return new ExceptionDTO(CONFLICT.value(), e.getMessage());
    }
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(CardNotFoundException.class)
    public static ExceptionDTO handleNotFoundException(RuntimeException e) {
        log.error("Not Found: {}", e.getMessage());
        return new ExceptionDTO(NOT_FOUND.value(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler({InsufficientFundsException.class, InvalidPasswordException.class, NonExistentCardException.class})
    public static ExceptionDTO handleUnprocessableEntityException(RuntimeException e) {
        log.error("Unprocessable Entity: {}", e.getMessage());
        return new ExceptionDTO(UNPROCESSABLE_ENTITY.value(), e.getMessage());
    }
}
