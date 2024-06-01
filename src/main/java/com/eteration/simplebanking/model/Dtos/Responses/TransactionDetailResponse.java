package com.eteration.simplebanking.model.Dtos.Responses;

public class TransactionDetailResponse {
    private String status;
    private String approvalCode;

    public TransactionDetailResponse(){
        this.status = status;
        this.approvalCode = approvalCode;
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
