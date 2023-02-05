package br.com.rembert.miniautorizador.util;

import br.com.rembert.miniautorizador.model.entity.Card;
import br.com.rembert.miniautorizador.repository.CardRepository;
import com.google.gson.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class Utils {

    public static <E> ResultActions mockMvcPost(MockMvc mockMvc, String path, E object) throws Exception {
        return mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(object)));

    }

    public static ResultActions mockMvcGet(MockMvc mockMvc, String path) throws Exception {
        return mockMvc.perform(get(path)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public static void createTestCard(CardRepository cardRepository, String cardNumber, String password) {
        Card card = Card.builder()
                .cardNumber(cardNumber)
                .password(password)
                .balance(BigDecimal.valueOf(500)).build();
        cardRepository.saveAndFlush(card);
    }

}

