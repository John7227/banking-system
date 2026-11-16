package com.bankingsystem.exceptions;

public class DuplicateAccountNumberException extends AccountException {
    public DuplicateAccountNumberException(String message) {
        super(message);
    }
}
