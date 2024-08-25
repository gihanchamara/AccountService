package com.mq.dto;

import com.mq.accounting.model.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String accountName;

    @Column(nullable = false, length = 50)
    private String accountType;

    @Column(nullable = false)
    private OffsetDateTime balanceDate;

    @Column(nullable = false, length = 3)
    private String currencyCode;

    @Column(nullable = false)
    private Long balance;

    // Constructor to convert from Account to AccountDTO
    public AccountDTO(Account account) {
        this.accountId = account.getAccountId();
        this.userId = account.getUserId();
        this.accountName = account.getAccountName();
        this.accountType = account.getAccountType();
        this.balanceDate = account.getBalanceDateTime();
        this.currencyCode = account.getCurrencyCode();
        this.balance = account.getBalance();
    }

    public Account toAccount() {
        Account account = new Account();
        account.setAccountId(this.accountId);
        account.setUserId(this.userId);
        account.setAccountName(this.accountName);
        account.setAccountType(this.accountType);
        account.setBalanceDateTime(this.balanceDate);
        account.setCurrencyCode(this.currencyCode);
        account.setBalance(this.balance);
        return account;
    }
}