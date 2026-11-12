package com.bankingsystem.controllers;

import com.bankingsystem.data.models.Account;
import com.bankingsystem.dtos.response.ApiResponse;
import com.bankingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/CreateAccount")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, accountService.createAccount(account)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
