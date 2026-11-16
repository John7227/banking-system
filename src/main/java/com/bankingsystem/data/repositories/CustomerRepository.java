package com.bankingsystem.data.repositories;

import com.bankingsystem.data.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
