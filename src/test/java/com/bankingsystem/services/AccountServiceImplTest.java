package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.models.Bank;
import com.bankingsystem.data.models.Customer;
import com.bankingsystem.data.models.enums.AccountType;
import com.bankingsystem.data.models.enums.BankCode;
import com.bankingsystem.data.repositories.AccountRepository;
import com.bankingsystem.data.repositories.BankRepository;
import com.bankingsystem.data.repositories.CustomerRepository;
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

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private String bankId;
    private String customerId;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
        bankRepository.deleteAll();
        customerRepository.deleteAll();

        Bank bank = new Bank();
        bank.setName("Access Bank");
        bank.setBankCode("044");
        bankId = bankRepository.save(bank).getId();

        Customer customer = new Customer();
        customer.setFirstName("Lawrence");
        customer.setLastName("John");
        customer.setEmail("lawjoh20@gmail.com");
        customerId = customerRepository.save(customer).getId();

    }

    @Test
    public void test_thatWhenICreateAccount_countIsOne_andReturnsAccountDetails() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20@gmail.com");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        CreateAccountResponse response = accountService.createAccount(request);

        assertEquals(1, accountRepository.count());
        assertNotNull(response.getId());
        assertTrue(response.getAccountNumber().matches("\\d{10}"));
        Account savedAccount = accountRepository.findAll().get(0);
        assertEquals(customerId, savedAccount.getCustomerId());
        assertEquals(bankId, savedAccount.getBankId());
    }

    @Test
    public void testCreateAccount_returnsAccountDetails() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20@gmail.com");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        CreateAccountResponse response = accountService.createAccount(request);

        assertNotNull(response.getId());
        assertEquals(AccountType.SAVINGS, response.getAccountType());
        assertEquals(BigDecimal.ZERO, response.getBalance());
        assertEquals(1, accountRepository.count());
        assertTrue(response.getAccountNumber().matches("\\d{10}"));
    }

    @Test
    public void testCreateAccountWhenCustomerEmailIsNull_throwsException() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail(null);
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        assertThrows(AccountException.class, () -> accountService.createAccount(request));

        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testCreateAccountWithoutCustomerEmail_throwsException() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail(" ");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        assertThrows(AccountException.class, ()-> accountService.createAccount(request));

        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testCreateAccountWithAnInvalidEmail_throwsException() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20gmail.com");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        assertThrows(AccountException.class, ()-> accountService.createAccount(request));

        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testCreateAccountWhenBankCodeDoesNotExist_throwsException() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20gmail.com");
        request.setBankCode(BankCode.CITIBANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        assertThrows(AccountException.class, ()-> accountService.createAccount(request));

        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testCreateAccountWithoutBankCode_throwsException() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20gmail.com");
        request.setBankCode(null);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        assertThrows(AccountException.class, ()-> accountService.createAccount(request));

        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testCreateAccountWhenAccountTypeDoesNotExist_throwsException() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20gmail.com");
        request.setBankCode(BankCode.CITIBANK);
        request.setAccountType(AccountType.CHECKING);
        request.setPin("1234");

        assertThrows(AccountException.class, ()-> accountService.createAccount(request));

        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testCreateAccountWithoutAccountType_throwsException() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20gmail.com");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(null);
        request.setPin("1234");

        assertThrows(AccountException.class, ()-> accountService.createAccount(request));

        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testFindAccountByAccountNumber_returnsAccount() {
        assertEquals(0, accountRepository.count());

        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerEmail("lawjoh20@gmail.com");
        request.setBankCode(BankCode.ACCESS_BANK);
        request.setAccountType(AccountType.SAVINGS);
        request.setPin("1234");

        CreateAccountResponse createdAccount = accountService.createAccount(request);

        CreateAccountResponse foundAccount = accountService.findAccountByAccountNumber(createdAccount.getAccountNumber());

        assertEquals(createdAccount.getId(), foundAccount.getId());
        assertEquals(createdAccount.getAccountNumber(), foundAccount.getAccountNumber());
        assertEquals(AccountType.SAVINGS, foundAccount.getAccountType());
        assertEquals(1, accountRepository.count());
    }

    @Test
    public void testFindAccountByAccountNumber_whenAccountDoesNotExist_throwsException() {
        assertEquals(0, accountRepository.count());

        assertThrows(AccountException.class, () -> accountService.findAccountByAccountNumber("9999999999"));
        assertEquals(0, accountRepository.count());
    }

    @Test
    public void testFindAccountByAccountNumber_whenNumberIsNull_throwsException() {
        assertThrows(AccountException.class, () -> accountService.findAccountByAccountNumber(null));
    }

}