package com.example.Bank.repository;



import java.util.List;

import com.example.Bank.dto.AccountDto;


public interface AccountRepository  {

   AccountDto createAccount (AccountDto accountDto);
   AccountDto getAccountById(Long Id);
   public List<AccountDto> getAllAccounts();
   AccountDto depositAmount(Long id , double amount);
   AccountDto withdrawAmount(Long id, double amount) throws Exception;
   void deleteAccount(Long id);
}
