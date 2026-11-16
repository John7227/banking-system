package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.repositories.AccountRepository;
import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;
import com.bankingsystem.exceptions.DuplicateAccountNumberException;
import com.bankingsystem.validator.AccountRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bankingsystem.utils.AccountMapper.toResponse;
import static com.bankingsystem.utils.AccountMapper.toAccount;
import static com.bankingsystem.utils.AccountNumberGenerator.generateAccountNumber;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        AccountRequestValidator.validate(request);

        String bankCode = request.getBankCode().getBankCode();
        String accountNumber = generateAccountNumber(bankCode);

        if(accountRepository.existsByAccountNumber(accountNumber)) {
            throw new DuplicateAccountNumberException("Account number already exists");
        }

        Account newAccount = toAccount(request, accountNumber);
        Account savedAccount = accountRepository.save(newAccount);

        return toResponse(savedAccount);
    }
}
