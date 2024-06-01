package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Dtos.Requests.AccountRequest;
import com.eteration.simplebanking.model.Dtos.Responses.AccountResponse;
import com.eteration.simplebanking.model.Dtos.Responses.TransactionDetailResponse;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.model.Enum.Messages;
import com.eteration.simplebanking.model.Exception.AccountCreateException;
import com.eteration.simplebanking.model.Mapper.ModelMapperManager;
import com.eteration.simplebanking.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

import java.util.*;


// This class is a place holder you can change the complete implementation
@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapperManager modelMapper;


    public AccountResponse getAccountDetails(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        AccountResponse response = modelMapper.getMapperResponse().map(account, AccountResponse.class);
        return response;
    }

    public Page<Account> findAllWithPagination(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public AccountResponse createAccount(AccountRequest accountRequest) throws AccountCreateException {
        Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountRequest.getAccountNumber());
        if (existingAccount.isPresent()) {
            throw new AccountCreateException(Messages.Values.AccountCreateException);
        } else {
            Account newAccount = new Account(accountRequest.getAccountNumber(), accountRequest.getOwner());
            Account savedAccount = accountRepository.save(newAccount);
            AccountResponse response = modelMapper.getMapperResponse().map(savedAccount, AccountResponse.class);
            return response;
        }
    }


    public TransactionDetailResponse credit(String accountNumber, DepositTransaction transaction) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.credit(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        TransactionDetailResponse response = new TransactionDetailResponse();
        TransactionDetailResponse result = response.transactionDetailResponseBuilder(transaction);
        transaction.setDate(new Date());
        accountRepository.save(account);


        return result;
    }

    public TransactionDetailResponse debit(String accountNumber, WithdrawalTransaction transaction) throws Throwable {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.debit(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        transaction.setDate(new Date());
        accountRepository.save(account);
        TransactionDetailResponse response = new TransactionDetailResponse();
        TransactionDetailResponse result = response.transactionDetailResponseBuilder(transaction);


        return result;


    }

    public TransactionDetailResponse billPayment(String accountNumber, BillPaymentTransaction transaction) throws Throwable {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.billPayment(transaction);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        transaction.setDate(new Date());
        accountRepository.save(account);
        TransactionDetailResponse response = new TransactionDetailResponse();
        TransactionDetailResponse result = response.transactionDetailResponseBuilder(transaction);


        return result;


    }

}
