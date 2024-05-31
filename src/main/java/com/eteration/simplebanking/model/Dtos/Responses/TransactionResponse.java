package com.eteration.simplebanking.model.Dtos.Responses;


import java.util.Date;


public class TransactionResponse {

        private String approvalCode;
        private Date date;
        private double amount;
        private String transactionType;

        public TransactionResponse(String approvalCode,Date date,double amount,String transactionType) {

            this.approvalCode =approvalCode;
            this.date = date;
            this.amount = amount;
            this.transactionType = transactionType;
        }



    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

