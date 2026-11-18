package com.bankingsystem.dtos.requests;

import com.bankingsystem.data.models.enums.AccountStatus;
import com.bankingsystem.data.models.enums.AccountType;
import com.bankingsystem.data.models.enums.BankCode;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateAccountRequest {

    private String customerEmail;
    private AccountType accountType;
    private BankCode bankCode;
    private String pin;

}
