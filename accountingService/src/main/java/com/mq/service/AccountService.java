package com.mq.service;

import com.mq.accounting.model.Account;
import com.mq.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<Account> getAccountsByUserId(Long userId);
}
