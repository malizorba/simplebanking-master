package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.Enum.TransactionType;

import javax.persistence.*;

@Entity
@DiscriminatorValue(TransactionType.Values.WITHDRAWAL)
// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction() {

    }


    public WithdrawalTransaction(double amount) {
        super(amount);
    }


    @Override
    public String toString() {
        return "Withdrawal - " + super.toString();
    }
}



