package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.Enum.TransactionType;

import javax.persistence.*;

@Entity
@DiscriminatorValue(TransactionType.Values.DEPOSIT)
// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction {


    public DepositTransaction() {

    }

    public DepositTransaction(double amount) {
        super(amount);

    }


    @Override
    public String toString() {
        return "Deposit - " + super.toString();
    }
}
