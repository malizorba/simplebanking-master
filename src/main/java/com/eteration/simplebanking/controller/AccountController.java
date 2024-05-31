package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Dtos.Requests.AccountRequest;
import com.eteration.simplebanking.model.Dtos.Responses.AccountResponse;
import com.eteration.simplebanking.model.Dtos.Responses.TransactionDetailResponse;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;


// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @ResponseBody
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber) throws Throwable {
        AccountResponse response = accountService.getAccountDetails(accountNumber);
        if (response == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionDetailResponse> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction transaction) throws Throwable {
        TransactionDetailResponse response;
        response = accountService.credit(accountNumber, transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionDetailResponse> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) throws Throwable {
        TransactionDetailResponse response;
        response = accountService.debit(accountNumber, transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/billPayment/{accountNumber}")
    public ResponseEntity<TransactionDetailResponse> billPayment(@PathVariable String accountNumber, @RequestBody BillPaymentTransaction transaction) throws Throwable {
        TransactionDetailResponse response;
        response = accountService.billPayment(accountNumber, transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse response;
        response = accountService.createAccount(accountRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}