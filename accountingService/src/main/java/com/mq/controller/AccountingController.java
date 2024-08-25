package com.mq.controller;

import com.mq.accounting.api.AccountApi;
import com.mq.accounting.model.Account;
import com.mq.service.AccountService;
import com.mq.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/accounting")
public class AccountingController implements AccountApi {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<List<Account>> getAccounts(Long userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
