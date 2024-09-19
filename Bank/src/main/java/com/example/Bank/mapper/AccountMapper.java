package com.example.Bank.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.Bank.dto.AccountDto;
import com.example.Bank.model.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(); // Use no-argument constructor
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());
        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );
    }

     public static List<AccountDto> mapToAccountDtoList(List<Account> accounts) {
        return accounts.stream()
                       .map(AccountMapper::mapToAccountDto)
                       .collect(Collectors.toList());
    }
}