package com.financeflow.financeflow_backend.controller;

import com.financeflow.financeflow_backend.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financeflow.financeflow_backend.service.TransactionService;
@AllArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionService transactionService;

    @PostMapping("/create/{id}")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO, @PathVariable("id") Long accountId) {
        TransactionDTO savedTransaction = transactionService.createTransaction(transactionDTO, accountId);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }
}