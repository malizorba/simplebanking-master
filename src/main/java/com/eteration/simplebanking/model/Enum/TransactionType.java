package com.eteration.simplebanking.model.Enum;

public enum TransactionType {

    BILLPAYMENT,
    WITHDRAWAL,
    DEPOSIT;

    public static class Values {

        public static final String BILLPAYMENT = "BILLPAYMENT_TRANSACTION";
        public static final String WITHDRAWAL = "WITHDRAWAL_TRANSACTION";
        public static final String DEPOSIT = "DEPOSIT_TRANSACTION";

    }
}
