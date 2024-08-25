package com.mq.persistence.repository;

import com.mq.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void findBySrcAccountId_shouldReturnPageOfTransactions() {
        Long srcAccountId = 1L;
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("transactionDateTime").descending());
        createTestTransactions(srcAccountId);
        Page<TransactionDTO> result = transactionRepository.findBySrcAccountId(srcAccountId, pageable);

        assertThat(result.getNumber()).isEqualTo(pageNumber);
        assertThat(result.getSize()).isEqualTo(pageSize);
        assertThat(result.getContent())
                .allMatch(transaction -> transaction.getSrcAccountId().equals(srcAccountId));
        assertThat(result.getContent())
                .isSortedAccordingTo((t1, t2) -> t2.getTransactionDateTime().compareTo(t1.getTransactionDateTime()));
    }

    private void createTestTransactions(Long srcAccountId) {
        List<TransactionDTO> transactions = List.of(
                createTransaction(1L, srcAccountId, 2L, OffsetDateTime.now().minusDays(1)),
                createTransaction(2L, srcAccountId, 3L, OffsetDateTime.now().minusHours(5)),
                createTransaction(3L, srcAccountId, 4L, OffsetDateTime.now().minusMinutes(30)),
                createTransaction(4L, 5L, srcAccountId, OffsetDateTime.now().minusHours(2))
        );
        transactionRepository.saveAll(transactions);
    }

    private TransactionDTO createTransaction(Long id, Long srcAccountId, Long destAccountId, OffsetDateTime dateTime) {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setTransactionId(id);
        transaction.setSrcAccountId(srcAccountId);
        transaction.setDestAccountId(destAccountId);
        transaction.setTransactionDateTime(dateTime);
        transaction.setCurrencyCode("USD");
        transaction.setIsDebit(true);
        transaction.setAmountInCents(1000L);
        transaction.setMemo("Test transaction");
        return transaction;
    }
}