package com.bankingsystem.data.repositories;

import com.bankingsystem.data.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BankRepository extends MongoRepository<Bank, String> {
    Optional<Bank> findByBankCode(String bankCode);
}
