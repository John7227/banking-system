package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

//    @Test
//    public void testCreationOfAccount() {
//        Account account = new Account();
//        account.setAccountNumber("1234567890");
//
//        Account savedAccount = accountService.createAccount(account);
//        assertEquals("1234567890", savedAccount.getAccountNumber());
//    }

}