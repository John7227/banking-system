package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.models.enums.AccountType;
import com.bankingsystem.data.models.enums.BankCode;
import com.bankingsystem.data.repositories.AccountRepository;
import com.bankingsystem.dtos.requests.CreateAccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    public void test_thatWhenICreateAccount_countIsOne() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setAccountNumber("1234567890");
        request.setCustomerId("customer-1");
        request.setBankId("bank-1");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setCurrencyCode("NGN");

        accountService.createAccount(request);

        assertEquals(1, accountRepository.count());
    }

}