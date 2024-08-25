package com.mq.persistence.repository;

import com.mq.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getAccountsByUserId_shouldReturnAccountsOwnedByUser() {
        Long userId = 1L;
        List<AccountDTO> accounts = accountRepository.getAccountsByUserId(userId);
        assertEquals(2, accounts.size());
        assertTrue(accounts.stream().allMatch(accountDTO -> accountDTO.getUserId().equals(userId)));
    }
}