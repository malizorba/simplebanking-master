package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.Exception.InsufficientBalanceException;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.Table;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(appliesTo = "Account")


public class Account {

    @Id
    private String accountNumber;
    private String owner;
    private double balance;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "AccountNumber")
    private List<Transaction> transactions;
    private LocalDate createDate;

    public Account() {

    }


    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.createDate = LocalDate.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
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


    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void post(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public WithdrawalTransaction debit(WithdrawalTransaction transaction) throws InsufficientBalanceException {
        if (transaction.getAmount() <= balance) {
            post(transaction);
            this.balance -= transaction.getAmount();
            return transaction;
        } else {
            throw new InsufficientBalanceException("Insufficient balance");
        }

    }

    public DepositTransaction credit(DepositTransaction transaction) {

        post(transaction);
        this.balance += transaction.getAmount();
        return transaction;

    }

    public BillPaymentTransaction billPayment(BillPaymentTransaction transaction) throws InsufficientBalanceException {
        if (transaction.getAmount() <= balance) {

            post(transaction);
            this.balance -= transaction.getAmount();
            return transaction;
        } else {
            throw new InsufficientBalanceException("Insufficient balance");
        }


    }

}
