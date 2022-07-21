package com.example.iroribank.controller;

import com.example.iroribank.api.AccountAPI;
import com.example.iroribank.exception.AccountNotFoundException;
import com.example.iroribank.model.dto.AccountRequest;
import com.example.iroribank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController implements AccountAPI {


  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }


  @Override
  public ResponseEntity updateAccount(AccountRequest accountRequest) {
    log.info("Updating account by accountId:{}", accountRequest.getId());
    try {
      accountService.updateAccount(accountRequest);
      return new ResponseEntity(HttpStatus.OK);
    } catch (AccountNotFoundException ex) {
      log.warn(ex.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity addAccount(AccountRequest accountRequest) {
    log.info("Adding new account");
    accountService.addAccount(accountRequest);
    return new ResponseEntity(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<AccountRequest> findAccount(Integer accountId) {
    log.info("Finding an account by id{}", accountId);
    try{
      AccountRequest accountResponse = accountService.getAccountById(accountId);
      return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    } catch(AccountNotFoundException ex){
      log.warn(ex.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity deleteAccount(Integer accountId) {
    log.info("Deleting account by id{}", accountId);
    try {
      accountService.deleteAccount(accountId);
      return new ResponseEntity(HttpStatus.OK);
    } catch(AccountNotFoundException ex){
      log.warn(ex.getMessage());
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }
}
