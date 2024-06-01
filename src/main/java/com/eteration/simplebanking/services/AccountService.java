package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Dtos.Requests.AccountRequest;
import com.eteration.simplebanking.model.Dtos.Responses.AccountResponse;
import com.eteration.simplebanking.model.Dtos.Responses.TransactionDetailResponse;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.model.Enum.Messages;
import com.eteration.simplebanking.model.Exception.AccountCreateException;
import com.eteration.simplebanking.model.Mapper.AccountMapper;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        AccountResponse response = AccountMapper.toAccountResponse(account);
        return response;
    }
    public Page<Account> findAllWithPagination(Pageable pageable){
        return accountRepository.findAll(pageable);
    }

    public AccountResponse createAccount(AccountRequest accountRequest) throws AccountCreateException {
        Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountRequest.getAccountNumber());
        if (existingAccount.isPresent()) {
            throw new AccountCreateException(Messages.Values.AccountCreateException);
        } else {
            Account newAccount = new Account(accountRequest.getAccountNumber(), accountRequest.getOwner());
            Account savedAccount = accountRepository.save(newAccount);
            return AccountMapper.toAccountResponse(savedAccount);
        }
    }


    public TransactionDetailResponse credit(String accountNumber, DepositTransaction transaction) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.credit(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        String approvalCode = transaction.getApprovalCode();
        transaction.setDate(new Date());
        accountRepository.save(account);
        TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();
        transactionDetailResponse.setApprovalCode(approvalCode);
        transactionDetailResponse.setStatus("OK");


        return transactionDetailResponse;
    }

    public TransactionDetailResponse debit(String accountNumber, WithdrawalTransaction transaction) throws Throwable {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.debit(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        String approvalCode = transaction.getApprovalCode();
        transaction.setDate(new Date());
        accountRepository.save(account);
        TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();
        transactionDetailResponse.setApprovalCode(approvalCode);
        transactionDetailResponse.setStatus("OK");


        return transactionDetailResponse;


    }

    public TransactionDetailResponse billPayment(String accountNumber, BillPaymentTransaction transaction) throws Throwable {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.billPayment(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        String approvalCode = transaction.getApprovalCode();
        transaction.setDate(new Date());
        accountRepository.save(account);
        TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();
        transactionDetailResponse.setApprovalCode(approvalCode);
        transactionDetailResponse.setStatus("OK");


        return transactionDetailResponse;


    }

}
