package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    Account createAccount(Account account);
}
