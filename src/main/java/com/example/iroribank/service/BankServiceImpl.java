package com.example.iroribank.service;

import com.example.iroribank.exception.AccountNotFoundException;
import com.example.iroribank.exception.AmountRestrictionException;
import com.example.iroribank.model.dto.Deposit;
import com.example.iroribank.model.dto.Withdrawal;
import com.example.iroribank.model.entity.Account;
import com.example.iroribank.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BankServiceImpl implements BankService{

  private final AccountRepository accountRepository;

  public BankServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Double getBalance(Integer accountId) {
    log.info("Starting getBalance with accountId{}", accountId);
    Optional<Account> accountInfo = accountRepository.findById(accountId);
    if(accountInfo.isPresent()) {
      return accountInfo.get().getBalance();
    } else {
      log.warn("Account not found with id{}", accountId);
      throw new AccountNotFoundException("Account not fount with id: " + accountId);
    }
  }

  @Override
  public Double depositToAccount(Integer accountId, Deposit deposit) {
    log.info("Starting depositToAccount with accountId {} and deposit amount {}", accountId, deposit.getAmount());
    Optional<Account> accountInfo = accountRepository.findById(accountId);
    if(accountInfo.isPresent()) {
      Account account = accountInfo.get();
      account.setBalance(deposit.getAmount() + account.getBalance());
      Account saveAccount = accountRepository.save(account);
      return saveAccount.getBalance();
    } else {
      log.warn("Account not found with id{}", accountId);
      throw new AccountNotFoundException("Account not fount with id: " + accountId);
    }
  }

  @Override
  public Double withdrawalFromAccount(Integer accountId, Withdrawal withdrawal) {
    log.info("Starting withdrawalFromAccount with accountId {}", accountId);
    Optional<Account> accountInfo = accountRepository.findById(accountId);
    if (accountInfo.isPresent()) {
      Account account = accountInfo.get();
      if (account.getBalance().compareTo(withdrawal.getAmount()) < 0) {
        throw new AmountRestrictionException("Amount is high! Balance is not enough!");
      } else {
        account.setBalance(account.getBalance() - (withdrawal.getAmount()));
        return accountRepository.save(account).getBalance();
      }
    } else {
      throw new AccountNotFoundException("Your Account Number is wrong!");
    }
  }
}
