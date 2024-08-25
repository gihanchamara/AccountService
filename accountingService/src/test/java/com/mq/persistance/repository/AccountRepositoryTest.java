package com.mq.persistance.repository;

import com.mq.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getAccountsByUserId() {
        List<AccountDTO> accounts = accountRepository.getAccountsByUserId(1L);
        assertEquals(1, accounts.size());
    }

    @Test
    void findByAccountId() {
        AccountDTO account = accountRepository.getReferenceById(1L);
        assertNotNull(account);
        assertEquals(1,account.toAccount().getAccountId());
    }
}