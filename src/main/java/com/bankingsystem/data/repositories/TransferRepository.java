package com.bankingsystem.data.repositories;

import com.bankingsystem.data.models.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransferRepository extends MongoRepository<Transfer, String> {
}
