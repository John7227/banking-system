package com.bankingsystem.services;

import com.bankingsystem.data.models.enums.AccountType;
import com.bankingsystem.data.models.enums.BankCode;
import com.bankingsystem.data.repositories.AccountRepository;
import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;
import com.bankingsystem.exceptions.AccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

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
        request.setCustomerId("customer-1");
        request.setBankId("bank-1");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setCurrencyCode("NGN");

        accountService.createAccount(request);

        assertEquals(1, accountRepository.count());
    }

//    @Test
//    public void testCreateAccount_returnsAccountDetails() {
//        assertEquals(0, accountRepository.count());
//
//        CreateAccountRequest request = new CreateAccountRequest();
//        request.setCustomerId("customer-1");
//        request.setBankId("bank-1");
//        request.setBankCode(BankCode.ACCESS_BANK);
//        request.setAccountType(AccountType.SAVINGS);
//        request.setCurrencyCode("NGN");
//
//        CreateAccountResponse response = accountService.createAccount(request);
//
//        assertNotNull(response.getId());
//        assertNotNull(response.getAccountNumber());
//        assertEquals(AccountType.SAVINGS, response.getAccountType());
//        assertEquals(BigDecimal.ZERO, response.getBalance());
//        assertEquals(1, accountRepository.count());
//        assertEquals(10, response.getAccountNumber().length());
//    }
//
//    @Test
//    public void testCreateAccountWhenCustomerIdIsNull_throwsException() {
//        assertEquals(0, accountRepository.count());
//
//        CreateAccountRequest request = new CreateAccountRequest();
//        request.setCustomerId(null);
//        request.setBankId("bank-1");
//        request.setBankCode(BankCode.ACCESS_BANK);
//        request.setAccountType(AccountType.SAVINGS);
//        request.setCurrencyCode("NGN");
//
//        assertThrows(AccountException.class, () -> accountService.createAccount(request));
//
//        assertEquals(0, accountRepository.count());
//    }

//    @Test
//    public void testCreateAccountWithoutCustomerId_throwsException() {
//        assertEquals(0, accountRepository.count());
//
//        CreateAccountRequest request = new CreateAccountRequest();
//        request.setCustomerId(" ");
//        request.setB;
//    }






//    @Test
//    public void testCreateAccountWithoutAccountType_throwsException() {}

}