package com.bankingsystem.dtos.response;

import com.bankingsystem.data.models.enums.AccountStatus;
import com.bankingsystem.data.models.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateAccountResponse {

    private String id;
    private String accountNumber;
    private String bankId;
    private AccountType accountType;
    private AccountStatus status;
    private BigDecimal balance;
    private String currencyCode;
    private LocalDate createdAt;
}
