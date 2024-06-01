package com.eteration.simplebanking.model.Dtos.Responses;

import com.eteration.simplebanking.model.Transaction;

public class TransactionDetailResponse {
    private String status;
    private String approvalCode;

    public TransactionDetailResponse() {
        this.status = status;
        this.approvalCode = approvalCode;
    }

    public TransactionDetailResponse transactionDetailResponseBuilder(Transaction transaction) {
        TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();
        transactionDetailResponse.setApprovalCode(transaction.getApprovalCode());
        transactionDetailResponse.setStatus("OK");
        return transactionDetailResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
