package br.com.rembert.miniautorizador.controller;

import br.com.rembert.miniautorizador.repository.CardRepository;
import br.com.rembert.miniautorizador.util.TransactionUtil;
import br.com.rembert.miniautorizador.util.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CardRepository cardRepository;


    private final static String PATH = "/transacoes";

    @Test
    void ItShouldMakeTransactionsToThrowInsufficientFundsException() throws Exception {
        Utils.createTestCard(cardRepository, "65498730256345012", "1234");
        Utils.mockMvcPost(mockMvc, PATH, TransactionUtil.getTransaction())
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("SALDO_INSUFICIENTE"));
    }

    @Test
    void ItShouldNotMakeATransactionWithAInvalidPassword() throws Exception {
        Utils.createTestCard(cardRepository, "65498730256345013", "1234");
        Utils.mockMvcPost(mockMvc, PATH, TransactionUtil.getTransactionWithInvalidPassword())
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("SENHA_INVALIDA"));
    }

    @Test
    void ItShouldNotMakeATransactionWithANonExistentCard() throws Exception {
        Utils.mockMvcPost(mockMvc, PATH, TransactionUtil.getTransactionWithNonExistentCard())
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("CARTAO_INEXISTENTE"));
    }

}
