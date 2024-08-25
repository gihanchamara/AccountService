package com.mq.service;

import com.mq.accounting.model.PaginatedTransactionResponse;

public interface TransactionService {
    PaginatedTransactionResponse getTransactions(Long accountId, Integer page, Integer size, String sort);
}
