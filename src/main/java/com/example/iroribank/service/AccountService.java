package com.example.iroribank.service;

import com.example.iroribank.exception.AccountNotFoundException;
import com.example.iroribank.model.dto.AccountRequest;
import com.example.iroribank.model.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  List<Account> getAllAccount();
  AccountRequest getAccountById(Integer id);
  void updateAccount(AccountRequest accountRequest) throws AccountNotFoundException;
  void addAccount(AccountRequest accountRequest);
  void deleteAccount(Integer id);
}
