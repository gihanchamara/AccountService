package com.mq.dto;

import com.mq.accounting.model.Transaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class TransactionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private Long srcAccountId;

    @Column(nullable = false)
    private Long destAccountId;

    @Column(nullable = false)
    private OffsetDateTime transactionDateTime;

    @Column(nullable = false, length = 3)
    private String currencyCode;

    @Column(nullable = false)
    private Boolean isDebit;

    @Column(nullable = false)
    private Long amountInCents;

    @Column(length = 255)
    private String memo;

    public TransactionDTO(Transaction transaction) {
        this.transactionId = transaction.getTransactionId();
        this.srcAccountId = transaction.getSrcAccountId();
        this.destAccountId = transaction.getDestAccountId();
        this.transactionDateTime = transaction.getTransactionDateTime();
        this.currencyCode = transaction.getCurrencyCode();
        this.isDebit = transaction.getIsDebit();
        this.amountInCents = transaction.getAmountInCents();
        this.memo = transaction.getMemo();
    }

    public Transaction toTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(this.transactionId);
        transaction.setSrcAccountId(this.srcAccountId);
        transaction.setDestAccountId(this.destAccountId);
        transaction.setTransactionDateTime(this.transactionDateTime);
        transaction.setCurrencyCode(this.currencyCode);
        transaction.setIsDebit(this.isDebit);
        transaction.setAmountInCents(this.amountInCents);
        transaction.setMemo(this.memo);
        return transaction;
    }
}