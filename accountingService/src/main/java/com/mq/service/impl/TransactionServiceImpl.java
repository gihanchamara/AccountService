package com.mq.service.impl;

import com.mq.accounting.model.PaginatedTransaction;
import com.mq.accounting.model.PaginatedTransactionPageable;
import com.mq.dto.TransactionDTO;
import com.mq.persistance.repository.TransactionRepository;
import com.mq.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public PaginatedTransaction getTransactions(Long accountId, Integer page, Integer size, String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        String property = "transactionDateTime";

        if (sort != null && !sort.isEmpty()) {
            if (sort.equalsIgnoreCase("desc")) {
                direction = Sort.Direction.DESC;
            } else if (!sort.equalsIgnoreCase("asc")) {
                // If it's not "asc" or "desc", assume it's a property name
                property = sort;
            }
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, property));
        Page<TransactionDTO> transactionPage = transactionRepository.findBySrcAccountId(accountId, pageable);

        PaginatedTransaction paginatedTransaction = new PaginatedTransaction();
        paginatedTransaction.content(transactionPage.getContent().stream()
                .map(TransactionDTO::toTransaction).toList());

        PaginatedTransactionPageable transactionPageable = new PaginatedTransactionPageable();
        transactionPageable.setPageNumber(transactionPage.getNumber());
        transactionPageable.setPageSize(transactionPage.getSize());
        paginatedTransaction.setPageable(transactionPageable);

        paginatedTransaction.setTotalPages(transactionPage.getTotalPages());
        paginatedTransaction.setTotalElements((int) transactionPage.getTotalElements());
        return paginatedTransaction;
    }
}
