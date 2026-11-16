package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.models.enums.AccountStatus;
import com.bankingsystem.data.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {

        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(LocalDate.now());
        return accountRepository.save(account);
    }
}
