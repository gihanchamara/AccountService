package com.mq.controller;

import com.mq.accounting.api.AccountsApi;
import com.mq.accounting.model.Account;
import com.mq.accounting.model.PaginatedTransactionResponse;
import com.mq.service.AccountService;
import com.mq.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class AccountingController implements AccountsApi {

    @Autowired
    private AccountService accountService;

    @Autowired
    private  TransactionService transactionService;

    @Override
    public ResponseEntity<List<Account>> getAccounts(Long userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedTransactionResponse> getTransactions(Long accountId, Integer page, Integer size, String sort) {
        PaginatedTransactionResponse transactions = transactionService.getTransactions(accountId, page, size, sort);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
