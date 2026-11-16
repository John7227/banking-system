package com.bankingsystem.utils;

import com.bankingsystem.exceptions.AccountException;

import java.util.Random;

public class AccountNumberGenerator {

    public static String generateAccountNumber(String bankCode) {
        Random input = new Random();
        String accountNumber = "";

        for(int count = 1; count <= 9; count++) {
            int myNumber = input.nextInt(10);
            accountNumber += myNumber;
        }

        return completeAccountNumber(bankCode, accountNumber);

    }

    public static String completeAccountNumber(String bankCode, String accountNumber) {
        if(bankCode == null || !bankCode.matches("\\d{3}") || accountNumber == null || !accountNumber.matches("\\d{9}"))
            throw new AccountException("Bank code must be 3 digits and serial number must be 9 digits");

        String totalSum = bankCode + accountNumber;

        int[] multipliers = {3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3};

        int result = 0;
        for(int index = 0; index < totalSum.length(); index++) {
            int currentNumber = Character.getNumericValue(totalSum.charAt(index));
            result += currentNumber * multipliers[index];
        }

        int lastDigit = result % 10;

        int calculatedResult = 10 - lastDigit;

        if(calculatedResult == 10){
            calculatedResult = 0;
        }

        return accountNumber + calculatedResult;

    }
}
