package com.bankingsystem.utils;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.models.enums.AccountStatus;
import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountMapper {

    public static Account toAccount(CreateAccountRequest request, String accountNumber) {
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setCustomerId(request.getCustomerId());
            account.setBankId(request.getBankId());
            account.setAccountType(request.getAccountType());
            account.setCurrencyCode(request.getCurrencyCode());
            account.setBalance(BigDecimal.ZERO);
            account.setStatus(AccountStatus.ACTIVE);
            account.setCreatedAt(LocalDate.now());
            return account;
        }

    public static CreateAccountResponse toResponse(Account account) {
        CreateAccountResponse response = new CreateAccountResponse();
        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        response.setBankId(account.getBankId());
        response.setAccountType(account.getAccountType());
        response.setStatus(account.getStatus());
        response.setBalance(account.getBalance());
        response.setCurrencyCode(account.getCurrencyCode());
        response.setCreatedAt(account.getCreatedAt());
        return response;
    }
}
