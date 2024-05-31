package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.Enum.TransactionType;

import javax.persistence.*;

@Entity
@DiscriminatorValue(TransactionType.Values.BILLPAYMENT)
public class BillPaymentTransaction extends Transaction {
    private String payee;


    public BillPaymentTransaction() {
    }

    public BillPaymentTransaction(double amount, String payee) {
        super(amount);
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    @Override
    public String toString() {
        return "Bill Payment to " + " - " + super.toString();
    }
}

