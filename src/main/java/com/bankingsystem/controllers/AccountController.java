package com.bankingsystem.controllers;

import com.bankingsystem.dtos.requests.CreateAccountRequest;
import com.bankingsystem.dtos.response.ApiResponse;
import com.bankingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/CreateAccount")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, accountService.createAccount(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/FindAccount/{accountNumber}")
    public ResponseEntity<?> findAccountByAccountNumber(@PathVariable String accountNumber) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, accountService.findAccountByAccountNumber(accountNumber)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
