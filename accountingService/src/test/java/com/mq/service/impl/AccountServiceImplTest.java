package com.mq.service.impl;

import com.mq.accounting.model.Account;
import com.mq.dto.AccountDTO;
import com.mq.persistence.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private AccountDTO accountDTO1;
    private AccountDTO accountDTO2;
    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        OffsetDateTime balanceDateTime = OffsetDateTime.now();
        accountDTO1 = new AccountDTO(1L, 1L, "Savings", "SAVINGS", balanceDateTime, "USD", 1000L);
        accountDTO2 = new AccountDTO(2L, 1L, "Checking", "CHECKING", balanceDateTime, "USD", 2000L);
        account1 = new Account().accountId(1L).userId(1L).accountName("Saving").accountType("SAVING").balanceDateTime(balanceDateTime).currencyCode("USD").balance(1000L);
        account2 = new Account().accountId(2L).userId(1L).accountName("Checking").accountType("CHECKING").balanceDateTime(balanceDateTime).currencyCode("USD").balance(2000L);
    }

    @Test
    void getAccountsByUserId_shouldReturnListOfAccounts() {
        Long userId = 1L;
        List<AccountDTO> accountDTOs = Arrays.asList(accountDTO1, accountDTO2);
        when(accountRepository.getAccountsByUserId(userId)).thenReturn(accountDTOs);
        List<Account> result = accountService.getAccountsByUserId(userId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(account1.getAccountId(), result.get(0).getAccountId());
        assertEquals(account2.getAccountId(), result.get(1).getAccountId());
        verify(accountRepository, times(1)).getAccountsByUserId(userId);
    }

    @Test
    void getAccountsByUserId_shouldReturnEmptyList_whenNoAccountsFound() {
        Long userId = 2L;
        when(accountRepository.getAccountsByUserId(userId)).thenReturn(List.of());
        List<Account> result = accountService.getAccountsByUserId(userId);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(accountRepository, times(1)).getAccountsByUserId(userId);
    }
}