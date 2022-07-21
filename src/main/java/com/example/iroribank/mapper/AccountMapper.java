package com.example.iroribank.mapper;

import com.example.iroribank.model.dto.AccountRequest;
import com.example.iroribank.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  Account accountRequestToAccount(AccountRequest accountRequest);
  AccountRequest accountToAccountRequest(Account account);
}
