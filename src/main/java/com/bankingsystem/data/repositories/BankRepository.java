package com.bankingsystem.data.repositories;

import com.bankingsystem.data.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String> {
}
