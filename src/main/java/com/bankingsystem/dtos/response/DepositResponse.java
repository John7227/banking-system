package com.bankingsystem.dtos.response;

import com.bankingsystem.data.models.enums.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DepositResponse {

    private String accountNumber;
    private BigDecimal depositedAmount;
    private String newBalance;
    private String transactionId;
    private TransactionStatus status;
    private LocalDate createdAt;
}
