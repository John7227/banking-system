package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.models.Bank;
import com.bankingsystem.data.models.Customer;
import com.bankingsystem.data.models.Transaction;
import com.bankingsystem.data.models.enums.TransactionStatus;
import com.bankingsystem.data.models.enums.TransactionType;
import com.bankingsystem.data.repositories.AccountRepository;
import com.bankingsystem.data.repositories.BankRepository;
import com.bankingsystem.data.repositories.CustomerRepository;
import com.bankingsystem.data.repositories.TransactionRepository;
import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.requests.DepositRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;
import com.bankingsystem.dtos.response.DepositResponse;
import com.bankingsystem.exceptions.AccountException;
import com.bankingsystem.exceptions.DuplicateAccountNumberException;
import com.bankingsystem.validator.AccountRequestValidator;
import com.bankingsystem.validator.DepositRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.bankingsystem.utils.AccountMapper.*;
import static com.bankingsystem.utils.AccountNumberGenerator.generateAccountNumber;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        AccountRequestValidator.validate(request);

        Optional<Customer> customer_result = customerRepository.findByEmail(request.getCustomerEmail());
        if(customer_result.isEmpty()) {
            throw new AccountException("Customer not found");
        }
        Customer customer = customer_result.get();

        String bankCode = request.getBankCode().getBankCode();

        Optional<Bank> bank_result = bankRepository.findByBankCode(bankCode);
        if(bank_result.isEmpty()) {
            throw new AccountException("Bank not found");
        }
        Bank bank = bank_result.get();

        String accountNumber = generateAccountNumber(bankCode);

        if(accountRepository.existsByAccountNumber(accountNumber)) {
            throw new DuplicateAccountNumberException("Account number already exists");
        }

        Account newAccount = toAccount(request, accountNumber, customer.getId(), bank.getId());
        Account savedAccount = accountRepository.save(newAccount);

        return toResponse(savedAccount);
    }

    @Override
    public CreateAccountResponse findAccountByAccountNumber(String accountNumber) {

        if(accountNumber == null || !accountNumber.matches("\\d{10}")) {
            throw new AccountException("Account number must be exactly 10 digits");
        }

        Optional<Account> result = accountRepository.findByAccountNumber(accountNumber);
        if(result.isEmpty()) {
            throw new AccountException("Account not found");
        }
        Account account = result.get();
        return toResponse(account);
    }

    @Override
    public DepositResponse deposit(DepositRequest request) {
        DepositRequestValidator.validate(request);

        Optional<Account> result = accountRepository.findByAccountNumber(request.getAccountNumber());
        if(result.isEmpty()) {
            throw new AccountException("Account not found");
        }
        Account account = result.get();
        if(!request.getPin().equals(account.getPin())) {
            throw new AccountException("Incorrect PIN");
        }
        account.setBalance(account.getBalance().add(request.getAmount()));
        Account updatedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(updatedAccount.getId());
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(request.getAmount());
        transaction.setStatus(TransactionStatus.SUCCESSFUL);
        transaction.setCreatedAt(LocalDate.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return toDepositResponse(updatedAccount, savedTransaction);
    }
}