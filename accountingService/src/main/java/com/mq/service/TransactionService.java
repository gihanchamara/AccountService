package com.mq.service;

import com.mq.accounting.model.PaginatedTransaction;

public interface TransactionService {
    PaginatedTransaction getTransactions(Long accountId, Integer page, Integer size, String sort);
}
