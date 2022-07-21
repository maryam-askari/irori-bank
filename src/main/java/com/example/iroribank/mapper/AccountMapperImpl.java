package com.example.iroribank.mapper;

import com.example.iroribank.model.dto.AccountRequest;
import com.example.iroribank.model.entity.Account;

public class AccountMapperImpl implements AccountMapper{

  @Override
  public Account accountRequestToAccount(AccountRequest accountRequest) {
    if(accountRequest == null){
      return null;
    }
    Account account = new Account();
    account.setBalance(accountRequest.getBalance());
    account.getCustomer().setId(accountRequest.getCustomerId());
    return account;
  }

  @Override
  public AccountRequest accountToAccountRequest(Account account) {
    if (account == null){
      return null;
    }
    AccountRequest accountRequest = new AccountRequest();
    accountRequest.setBalance(account.getBalance());
    accountRequest.setCustomerId(account.getCustomer().getId());
    return accountRequest;
  }
}
