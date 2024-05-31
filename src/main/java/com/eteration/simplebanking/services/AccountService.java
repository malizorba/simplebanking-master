package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Dtos.Requests.AccountRequest;
import com.eteration.simplebanking.model.Dtos.Responses.AccountResponse;
import com.eteration.simplebanking.model.Dtos.Responses.TransactionDetailResponse;
import com.eteration.simplebanking.model.Dtos.Responses.TransactionResponse;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.*;


// This class is a place holder you can change the complete implementation
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public AccountResponse getAccountDetails(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        List<TransactionResponse> transactionResponses = getTransactionResponses(account);
        AccountResponse response = new AccountResponse(account.getAccountNumber(), account.getOwner(), account.getBalance(), account.getCreateDate(), transactionResponses);

        return response;
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {
        Account account = new Account(accountRequest.getOwner(), accountRequest.getAccountNumber());
        account.setBalance(0);
        List<TransactionResponse> transactionResponses = getTransactionResponses(account);

        accountRepository.save(account);
        AccountResponse accountResponse = new AccountResponse(account.getAccountNumber(), account.getOwner(), account.getBalance(), account.getCreateDate(), transactionResponses);
        return accountResponse;
    }

    private static List<TransactionResponse> getTransactionResponses(Account account) {
        List<TransactionResponse> transactionResponses = account.getTransactions().stream().map(transaction -> {
            return new TransactionResponse(

                    transaction.getApprovalCode(),
                    transaction.getDate(),
                    transaction.getAmount(),
                    transaction.getDecriminatorValue()

            );
        }).toList();
        return transactionResponses;
    }

    public TransactionDetailResponse credit(String accountNumber, DepositTransaction transaction) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.credit(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        String approvalCode = transaction.getApprovalCode();
        transaction.setDate(new Date());
        accountRepository.save(account);


        return new TransactionDetailResponse("OK", approvalCode);
    }

    public TransactionDetailResponse debit(String accountNumber, WithdrawalTransaction transaction) throws Throwable {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.debit(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        String approvalCode = transaction.getApprovalCode();
        transaction.setDate(new Date());
        accountRepository.save(account);

        return new TransactionDetailResponse("OK", approvalCode);
    }

    public TransactionDetailResponse billPayment(String accountNumber, BillPaymentTransaction transaction) throws Throwable {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.billPayment(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        String approvalCode = transaction.getApprovalCode();
        transaction.setDate(new Date());
        accountRepository.save(account);

        return new TransactionDetailResponse("OK", approvalCode);
    }

}
