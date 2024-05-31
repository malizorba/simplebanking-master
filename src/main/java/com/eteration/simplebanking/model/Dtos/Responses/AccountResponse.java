package com.eteration.simplebanking.model.Dtos.Responses;

import java.time.LocalDate;
import java.util.List;

public class AccountResponse {



    private String accountNumber;
    private String owner;
    private double balance;
    private LocalDate createDate;
    private List<TransactionResponse> transactions;




    public AccountResponse(String accountNumber, String owner, double balance, LocalDate createDate,List<TransactionResponse> transactions ) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance =balance;
        this.createDate = createDate;
        this.transactions = transactions;


    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }
}
