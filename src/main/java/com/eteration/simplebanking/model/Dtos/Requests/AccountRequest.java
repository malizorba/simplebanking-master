package com.eteration.simplebanking.model.Dtos.Requests;


import lombok.NoArgsConstructor;


@NoArgsConstructor
public class AccountRequest {


    private String accountNumber;
    private String owner;

    public AccountRequest(String accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
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

}
