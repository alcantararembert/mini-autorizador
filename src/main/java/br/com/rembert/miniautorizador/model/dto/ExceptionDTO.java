package br.com.rembert.miniautorizador.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionDTO {
    private int status;
    private String message;

}
