package com.bankingsystem.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {

    private String accountNumber;
    private String pin;
    private BigDecimal amount;
}
