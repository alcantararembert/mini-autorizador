package br.com.rembert.miniautorizador.controller;

import br.com.rembert.miniautorizador.model.dto.TransactionDto;
import br.com.rembert.miniautorizador.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(path = "transacoes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<String> executeTransaction(@RequestBody TransactionDto transactionDto) {
        log.info("Executing a transaction to card number: {}", transactionDto.getNumeroCartao());
        return new ResponseEntity<>(this.transactionService.executeTransaction(transactionDto), HttpStatus.CREATED);
    }

}
