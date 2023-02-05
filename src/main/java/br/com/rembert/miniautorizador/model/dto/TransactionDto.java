package br.com.rembert.miniautorizador.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @Valid
    @NotNull
    private String numeroCartao;
    @Valid
    @NotNull
    private String senhaCartao;
    @Valid
    @NotNull
    private BigDecimal valor;
}
