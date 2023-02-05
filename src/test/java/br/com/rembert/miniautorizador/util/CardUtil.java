package br.com.rembert.miniautorizador.util;

import br.com.rembert.miniautorizador.model.dto.CardDto;


public class CardUtil {
    public static CardDto getValidCard() {
        return CardDto.builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .build();
    }
    public static CardDto getNotDuplicateACard() {
        return CardDto.builder()
                .numeroCartao("6549873025634502")
                .senha("1234")
                .build();
    }


}
