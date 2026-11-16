package com.bankingsystem.dtos.requests;

import com.bankingsystem.data.models.enums.AccountStatus;
import com.bankingsystem.data.models.enums.AccountType;
import com.bankingsystem.data.models.enums.BankCode;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateAccountRequest {

    private String accountNumber;
    private AccountStatus status;
    private BigDecimal balance;
    private AccountType accountType;
    private String currencyCode;
    private String customerId;
    private BankCode bankCode;
    private String bankId;
    private LocalDate createdAt;

}
