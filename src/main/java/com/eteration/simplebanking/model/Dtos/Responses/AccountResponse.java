package com.eteration.simplebanking.model.Dtos.Responses;

import com.eteration.simplebanking.model.Transaction;
import java.time.LocalDate;
import java.util.List;

public class AccountResponse {



    private String accountNumber;
    private String owner;
    private double balance;
    private LocalDate createDate;
    private List<Transaction> transactions;



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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
