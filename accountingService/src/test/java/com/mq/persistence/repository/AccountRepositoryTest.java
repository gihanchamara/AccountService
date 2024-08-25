package com.mq.persistence.repository;

import com.mq.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getAccountsByUserId() {
        List<AccountDTO> accounts = accountRepository.getAccountsByUserId(1L);
        assertEquals(2, accounts.size());
    }

    @Test
    void findByAccountId() {
        AccountDTO account = accountRepository.getReferenceById(1L);
        assertNotNull(account);
        assertEquals(1,account.toAccount().getAccountId());
    }
}