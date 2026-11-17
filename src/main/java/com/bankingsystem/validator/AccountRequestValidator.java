package com.bankingsystem.validator;

import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.exceptions.AccountException;

public class AccountRequestValidator {

    public static void validate(CreateAccountRequest request) {
        if (request == null) {
            throw new AccountException("Request is required");
        }
        if (isBlank(request.getCustomerEmail())) {
            throw new AccountException("Customer email is required");
        }
        if (request.getBankCode() == null) {
            throw new AccountException("Bank code is required");
        }
        if (request.getAccountType() == null) {
            throw new AccountException("Account type is required");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

}
