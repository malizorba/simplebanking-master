package com.eteration.simplebanking.model.Mapper;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Dtos.Responses.AccountResponse;

public class AccountMapper {
  public static  AccountResponse toAccountResponse(Account account) {
      AccountResponse accountResponse = new AccountResponse();
      accountResponse.setAccountNumber(account.getAccountNumber());
      accountResponse.setOwner(account.getOwner());
      accountResponse.setBalance(account.getBalance());
      accountResponse.setCreateDate(account.getCreateDate());
      accountResponse.setTransactions(account.getTransactions());
      return accountResponse;
  }
  public static Account toEntity(AccountResponse accountResponse) {
      Account account = new Account();
      account.setAccountNumber(accountResponse.getAccountNumber());
      account.setOwner(accountResponse.getOwner());
      account.setBalance(accountResponse.getBalance());
      account.setCreateDate(accountResponse.getCreateDate());
      account.setTransactions(accountResponse.getTransactions());
      return account;
  }
}
