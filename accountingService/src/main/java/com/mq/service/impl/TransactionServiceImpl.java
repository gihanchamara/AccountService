package com.mq.service.impl;

import com.mq.accounting.model.PaginatedTransaction;
import com.mq.accounting.model.PaginatedTransactionPageable;
import com.mq.dto.TransactionDTO;
import com.mq.exception.TransactionServiceException;
import com.mq.persistence.repository.TransactionRepository;
import com.mq.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Sort DEFAULT_SORT = Sort.by(Sort.Direction.DESC, "transactionDateTime");

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    @Override
    public PaginatedTransaction getTransactions(Long accountId, Integer page, Integer size, String sort) {
        //TODO : introduce sortable fields as enum and simplify the logic
        Pageable pageable = PageRequest.of(page, size, parseSort(sort));
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

    public static Sort parseSort(String sortParam) throws TransactionServiceException {
        if (sortParam == null || sortParam.trim().isEmpty()) {
            return DEFAULT_SORT;
        }

        String[] parts = sortParam.split(",");
        if (parts.length != 2) {
            throw new TransactionServiceException("Invalid sort parameter. Format should be 'property,direction'");
        }

        String property = parts[0].trim();
        String direction = parts[1].trim().toLowerCase();

        Sort.Direction sortDirection;
        if ("asc".equals(direction)) {
            sortDirection = Sort.Direction.ASC;
        } else if ("desc".equals(direction)) {
            sortDirection = Sort.Direction.DESC;
        } else {
            throw new TransactionServiceException("Invalid sort direction. Use 'asc' or 'desc'");
        }

        if (!isValidSortProperty(property)) {
            throw new TransactionServiceException("Invalid sort property: " + property);
        }

        return Sort.by(sortDirection, property);
    }

    private static boolean isValidSortProperty(String property) {
        return "transactionDateTime".equals(property) || "amountInCents".equals(property);
    }
}
