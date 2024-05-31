package com.eteration.simplebanking.model.Enum;

public enum ExcepitonMessages {

    InsufficientBalance,
    AccountCreateException;


    public static class Values {

        public static final String  InsufficientBalance = "INSUFFICIENT_BALANCE_EXCEPTION";
        public static final String  AccountCreateException = "An account with the same account number already exists.";


    }
}
