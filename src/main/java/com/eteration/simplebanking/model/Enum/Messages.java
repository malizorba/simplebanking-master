package com.eteration.simplebanking.model.Enum;

public enum Messages {

    InsufficientBalance,
    AccountCreateException,
    AccountNumber;


    public static class Values {

        public static final String  InsufficientBalance = "INSUFFICIENT_BALANCE_EXCEPTION";
        public static final String  AccountCreateException = "An account with the same account number already exists.";
        public static final String  AccountNumber = "Account number must be in the format XXX-XXXX";



    }
}
