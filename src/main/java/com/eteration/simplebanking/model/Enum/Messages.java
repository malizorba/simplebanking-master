package com.eteration.simplebanking.model.Enum;

public enum Messages {

    InsufficientBalance,
    AccountCreateException,
    AccountNumber,
    NameSurnameNotBlank,
    UnAcceptableNameSurname;


    public static class Values {

        public static final String InsufficientBalance = "INSUFFICIENT_BALANCE_EXCEPTION";
        public static final String AccountCreateException = "An account with the same account number already exists.";
        public static final String AccountNumber = "Account Number Must Be In the Format XXX-XXXX";
        public static final String NameSurnameNotBlank = "Name and Surname Cannot Be Blank.";
        public static final String UnAcceptableNameSurname = "Unacceptable Name and Surname Format ";


    }
}
