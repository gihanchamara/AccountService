package com.mq.service.impl;

import com.mq.accounting.model.Account;
import com.mq.dto.AccountDTO;
import com.mq.persistence.repository.AccountRepository;
import com.mq.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        List<AccountDTO> accounts = accountRepository.getAccountsByUserId(userId);
        return accounts.stream().map(AccountDTO::toAccount).toList();
    }
}
