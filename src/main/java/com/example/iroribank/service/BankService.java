package com.example.iroribank.service;

import com.example.iroribank.model.dto.Deposit;
import com.example.iroribank.model.dto.Withdrawal;

public interface BankService {

  Double getBalance(Integer accountId);
  Double depositToAccount(Integer accountId, Deposit deposit);
  Double withdrawalFromAccount(Integer accountId, Withdrawal withdrawal);
}
