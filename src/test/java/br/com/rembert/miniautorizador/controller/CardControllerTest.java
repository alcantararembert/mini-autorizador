package br.com.rembert.miniautorizador.controller;

import br.com.rembert.miniautorizador.exception.CardNumberAlreadyDefinedException;
import br.com.rembert.miniautorizador.exception.InsufficientFundsException;
import br.com.rembert.miniautorizador.model.entity.Card;
import br.com.rembert.miniautorizador.repository.CardRepository;
import br.com.rembert.miniautorizador.util.CardUtil;
import br.com.rembert.miniautorizador.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CardRepository cardRepository;

    private final static String PATH = "/cartoes";

     @Test
    void ItShouldCreateCard() throws Exception {
         Utils.mockMvcPost(mockMvc, PATH, CardUtil.getValidCard())
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao").value("6549873025634501"))
                .andExpect(jsonPath("$.senha").value("1234"));
    }
    @Test
    void ItShouldNotDuplicateACard() throws Exception {
         Utils.createTestCard(cardRepository, "6549873025634502", "1234");
         Utils.mockMvcPost(mockMvc, PATH, CardUtil.getNotDuplicateACard())
                 .andDo(print())
                 .andExpect(status().isConflict())
                 .andExpect(result -> assertTrue(result.getResolvedException() instanceof CardNumberAlreadyDefinedException));
    }

    @Test
    void ItShouldVerifyBalanceOfANewCard() throws Exception {
        Utils.createTestCard(cardRepository, "6549873025634503", "1234");
        String response = Utils.mockMvcGet(mockMvc, PATH.concat("/6549873025634503"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(response, "500.00");
    }

}
