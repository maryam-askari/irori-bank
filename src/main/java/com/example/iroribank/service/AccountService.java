package com.example.iroribank.service;

import com.example.iroribank.exception.AccountNotFoundException;
import com.example.iroribank.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  List<Account> getAllAccount();
  Account getAccountById(Integer id);
  void updateAccount(Integer id, Account account) throws AccountNotFoundException;
  void addAccount(Account account);
  void deleteAccount(Integer id);
}
