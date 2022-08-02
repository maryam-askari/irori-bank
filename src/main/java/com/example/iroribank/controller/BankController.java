package com.example.iroribank.controller;

import com.example.iroribank.api.BankApi;
import com.example.iroribank.exception.AccountNotFoundException;
import com.example.iroribank.exception.AmountRestrictionException;
import com.example.iroribank.model.dto.Deposit;
import com.example.iroribank.model.dto.Withdrawal;
import com.example.iroribank.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class BankController implements BankApi {

  private final BankService bankService;

  public BankController(BankService bankService) {
    this.bankService = bankService;
  }

  @Override
  public ResponseEntity<Double> getBalance(Integer accountId) {
    log.info("Start getBalance by accountId {}", accountId);
    try{
      return new ResponseEntity<>(bankService.getBalance(accountId) , HttpStatus.OK);
    } catch (AccountNotFoundException e){
      log.warn(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<Double> depositToAccount(Integer accountId, Deposit deposit) {
    log.info("starting depositToAccount by accountId {}", accountId);
    try {
      return new ResponseEntity<>(bankService.depositToAccount(accountId,deposit), HttpStatus.OK);
    }catch (AccountNotFoundException e){
      log.warn(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<Double> withdrawalFromAccount(Integer accountId, Withdrawal withdrawal) {
    log.info("Starting withdrawalFromAccount method {" + accountId + "}");
    try{
      return new ResponseEntity<>(bankService.withdrawalFromAccount(accountId, withdrawal), HttpStatus.OK);
    }catch (AccountNotFoundException e){
      log.warn(e.getMessage());
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }catch (AmountRestrictionException ex){
      log.warn(ex.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
