package com.bankingsystem.services;

import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest request);
    CreateAccountResponse findAccountByAccountNumber(String accountNumber);
}
