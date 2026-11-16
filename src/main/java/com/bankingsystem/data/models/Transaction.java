package com.bankingsystem.data.models;

import com.bankingsystem.data.models.enums.TransactionStatus;
import com.bankingsystem.data.models.enums.TransactionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document
public class Transaction {

    @Id
    private String id;
    private String accountId;
    private String transferId;
    private TransactionType type;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDate createdAt;
}
