package com.mq.controller;

import com.mq.accounting.api.TransactionApi;
import com.mq.accounting.model.PaginatedTransaction;
import com.mq.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounting")
public class TransactionController implements TransactionApi {

    @Autowired
    private TransactionService transactionService;

    @Override
    public ResponseEntity<PaginatedTransaction> getTransactions(Long accountId, Integer page, Integer size, String sort) {
        PaginatedTransaction transactions = transactionService.getTransactions(accountId, page, size, sort);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
