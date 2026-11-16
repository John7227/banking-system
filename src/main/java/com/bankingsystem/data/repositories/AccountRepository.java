package com.bankingsystem.data.repositories;

import com.bankingsystem.data.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    boolean existsByAccountNumber(String accountNumber);
}
