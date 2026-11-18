package com.bankingsystem.validator;

import com.bankingsystem.dtos.requests.DepositRequest;
import com.bankingsystem.exceptions.AccountException;

import java.math.BigDecimal;

public class DepositRequestValidator {

    public static void validate(DepositRequest request) {
        if(request == null) {
            throw new AccountException("Deposit request is required");
        }
        if(request.getAccountNumber() == null || !request.getAccountNumber().matches("\\d{10}")) {
            throw new AccountException("Account number must be exactly 10 digits");
        }
        if(request.getPin() == null || !request.getPin().matches("\\d{4}")) {
            throw new AccountException("PIN must be exactly 4 digits");
        }
        if(request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new AccountException("Deposit amount must be greater than zero");
        }
    }
}
