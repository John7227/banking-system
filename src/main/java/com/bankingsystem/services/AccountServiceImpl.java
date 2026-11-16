package com.bankingsystem.services;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.data.models.Bank;
import com.bankingsystem.data.models.Customer;
import com.bankingsystem.data.repositories.AccountRepository;
import com.bankingsystem.data.repositories.BankRepository;
import com.bankingsystem.data.repositories.CustomerRepository;
import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.response.CreateAccountResponse;
import com.bankingsystem.exceptions.AccountException;
import com.bankingsystem.exceptions.DuplicateAccountNumberException;
import com.bankingsystem.validator.AccountRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bankingsystem.utils.AccountMapper.toResponse;
import static com.bankingsystem.utils.AccountMapper.toAccount;
import static com.bankingsystem.utils.AccountNumberGenerator.generateAccountNumber;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankRepository bankRepository;

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
}