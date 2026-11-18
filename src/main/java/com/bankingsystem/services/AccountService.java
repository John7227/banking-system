package com.bankingsystem.services;

import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.requests.DepositRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;
import com.bankingsystem.dtos.response.DepositResponse;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest request);
    CreateAccountResponse findAccountByAccountNumber(String accountNumber);
    DepositResponse deposit(DepositRequest request);
}
