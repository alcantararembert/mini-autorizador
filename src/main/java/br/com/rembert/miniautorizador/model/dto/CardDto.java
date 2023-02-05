package br.com.rembert.miniautorizador.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    @Valid
    @NotNull
    private String numeroCartao;
    @Valid
    @NotNull
    private String senha;
}
