package com.eteration.simplebanking;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {

    @Test
    public void testCreateAccountAndSetBalance0() {
        Account account = new Account("17892", "Kerem Karaca");
        assertTrue(account.getOwner().equals("Kerem Karaca"));
        assertTrue(account.getAccountNumber().equals("17892"));
        assertTrue(account.getBalance() == 0);
    }

    @Test
    public void testDepositIntoBankAccount() {
        Account account = new Account("Demet Demircan", "9834");
        account.credit(new DepositTransaction(100));
        assertTrue(account.getBalance() == 100);
    }

    @Test
    public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
        Account account = new Account("Demet Demircan", "9834");
        account.credit(new DepositTransaction(100));
        assertTrue(account.getBalance() == 100);
        account.debit(new WithdrawalTransaction(50));
        assertTrue(account.getBalance() == 50);
    }

    @Test
    public void testWithdrawException() {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            Account account = new Account("Demet Demircan", "9834.0");
            account.debit(new WithdrawalTransaction(1000));
            account.credit(new DepositTransaction(2000));
        });

    }

    @Test
    public void testTransactions() throws InsufficientBalanceException {
        // Create account
        Account account = new Account("Canan Kaya", "1234");
        assertTrue(account.getTransactions().size() == 0);

        // Deposit Transaction
        DepositTransaction depositTrx = new DepositTransaction(100);
        assertTrue(depositTrx.getDate() != null);
        account.credit(depositTrx);
        assertTrue(account.getBalance() == 100);
        assertTrue(account.getTransactions().size() == 1);

        // Withdrawal Transaction
        WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
        assertTrue(withdrawalTrx.getDate() != null);
        account.debit(withdrawalTrx);
        assertTrue(account.getBalance() == 40);
        assertTrue(account.getTransactions().size() == 2);
        //assertEquals(0,account.getTransactions());
    }
}
