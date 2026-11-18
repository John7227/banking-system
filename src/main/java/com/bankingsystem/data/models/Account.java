package com.bankingsystem.data.models;

import com.bankingsystem.data.models.enums.AccountStatus;
import com.bankingsystem.data.models.enums.AccountType;
import com.bankingsystem.data.models.enums.BankCode;
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
    private String pin;
    private AccountStatus status;
    private BigDecimal balance;
    private AccountType accountType;
    private String customerId;
    private String bankId;
    private BankCode bankCode;
    private LocalDate createdAt;
}
