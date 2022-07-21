package com.example.iroribank.service;

import com.example.iroribank.exception.AccountNotFoundException;
import com.example.iroribank.mapper.AccountMapper;
import com.example.iroribank.model.dto.AccountRequest;
import com.example.iroribank.model.entity.Account;
import com.example.iroribank.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

  private AccountMapper mapper;

  private final AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public List<Account> getAllAccount() {
    log.info("Getting all accounts");
    return accountRepository.findAll();
  }

  @Override
  public AccountRequest getAccountById(Integer id) {
    log.info("Getting account by id{}", id);
    Optional<Account> account = accountRepository.findById(id);
    if (account.isPresent()){
      AccountRequest accountRequest = mapper.accountToAccountRequest(account.get());
      return accountRequest;
    }else {
      log.warn("Account not found with id{}", id);
      throw new AccountNotFoundException("Account not fount with id: " + id);
    }
  }

  @Override
  public void updateAccount(AccountRequest accountRequest) throws AccountNotFoundException {
    log.info("Updating account by accountId {}", accountRequest.getId());
    Optional<Account> accountData = accountRepository.findById(accountRequest.getId());
    if (accountData.isPresent()) {
      Account accountInfo = accountData.get();
      if(accountInfo.getCustomer().getId().equals(accountRequest.getCustomerId())) {
       Account account = mapper.accountRequestToAccount(accountRequest);
        accountRepository.save(accountInfo);
      } else {
        log.warn("Can not find customerId{} by accountId{}", accountRequest.getCustomerId(), accountRequest.getId());
      }
    } else {
      log.warn("Account can not find by id{}",accountRequest.getId());
      throw new AccountNotFoundException("Cannot fid account with id: " + accountRequest.getId());
    }
  }

  @Override
  public void addAccount(AccountRequest accountRequest) {
    log.info("Adding new account");
    Account account = mapper.accountRequestToAccount(accountRequest);
    accountRepository.save(account);
  }

  @Override
  public void deleteAccount(Integer id) {
    log.info("Deleting account by id{}", id);
    Optional<Account> accountData = accountRepository.findById(id);
    if (accountData.isPresent()) {
      accountRepository.deleteById(id);
    } else {
      log.warn("Account can not find by id{}", id);
      throw new AccountNotFoundException("Cannot fid account with id: " + id);
    }
  }
}
