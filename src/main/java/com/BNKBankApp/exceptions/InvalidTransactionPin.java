package com.BNKBankApp.exceptions;

public class InvalidTransactionPin extends RuntimeException {
    public InvalidTransactionPin(String message) {
        super(message);
    }
}
