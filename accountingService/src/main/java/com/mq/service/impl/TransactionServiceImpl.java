package com.mq.service.impl;

import com.mq.accounting.model.PaginatedTransactionResponse;
import com.mq.accounting.model.PaginatedTransactionResponsePageable;
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

    public PaginatedTransactionResponse getTransactions(Long accountId, Integer page, Integer size, String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        String property = "transactionDateTime";

        if (sort != null && !sort.isEmpty()) {
            String[] parts = sort.split(",");
            direction = parts[0].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            if (parts.length > 1) {
                property = parts[1];
            }
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, property));
        Page<TransactionDTO> transactionPage = transactionRepository.findBySrcAccountIdOrDestAccountId(accountId, accountId, pageable);

        PaginatedTransactionResponse response = new PaginatedTransactionResponse();
        response.setContent(transactionPage.getContent().stream()
                .map(TransactionDTO::toTransaction).toList());

        PaginatedTransactionResponsePageable responsePageable = new PaginatedTransactionResponsePageable();
        responsePageable.setPageNumber(transactionPage.getNumber());
        responsePageable.setPageSize(transactionPage.getSize());
        response.setPageable(responsePageable);

        response.setTotalPages(transactionPage.getTotalPages());
        response.setTotalElements((int) transactionPage.getTotalElements());
        return response;
    }
}
