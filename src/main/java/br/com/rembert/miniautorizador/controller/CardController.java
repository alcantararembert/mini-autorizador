package br.com.rembert.miniautorizador.controller;

import br.com.rembert.miniautorizador.model.dto.CardDto;
import br.com.rembert.miniautorizador.service.CardService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Log4j2
@RestController
@RequestMapping(path = "cartoes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping()
    public ResponseEntity<CardDto> createCard(@RequestBody @Valid CardDto cardDto) {
        log.info("Creating a card number: {}", cardDto.getNumeroCartao());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cardService.createCard(cardDto));
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable(name = "numeroCartao") String cardNumber) {
        log.info("Get balance from card number: {}", cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(this.cardService.getBalance(cardNumber));
    }

}
