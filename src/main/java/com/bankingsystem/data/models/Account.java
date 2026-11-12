package com.bankingsystem.data.models;

import com.bankingsystem.data.models.enums.AccountStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document
public class Account {

    @Id
    private String id;
    private String accountNumber;
    private AccountStatus status;
    private BigDecimal balance;
    private String currencyCode;
    private String customerId;
    private String bankId;
    private LocalDate createdAt;
}
