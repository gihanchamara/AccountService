package com.mq.exception;

public class TransactionServiceException extends RuntimeException{
    public TransactionServiceException(String message) {
        super(message);
    }
}
