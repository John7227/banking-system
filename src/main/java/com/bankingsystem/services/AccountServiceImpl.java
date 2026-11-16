package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.models.enums.AccountStatus;
import com.bankingsystem.data.repositories.AccountRepository;
import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;
import com.bankingsystem.exceptions.DuplicateAccountNumberException;
import com.bankingsystem.validator.AccountRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bankingsystem.utils.AccountMapper.toResponse;
import static com.bankingsystem.utils.AccountMapper.toAccount;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        AccountRequestValidator.validate(request);
        if(accountRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new DuplicateAccountNumberException("Account number already exists");
        }

        Account newAccount = toAccount(request, request.getAccountNumber());
        Account savedAccount = accountRepository.save(newAccount);

        return toResponse(savedAccount);
    }
}
