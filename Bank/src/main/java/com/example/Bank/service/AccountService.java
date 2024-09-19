package com.example.Bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bank.repository.AccountJpaRepository;
import com.example.Bank.repository.AccountRepository;
import com.example.Bank.dto.AccountDto;
import com.example.Bank.mapper.AccountMapper;
import com.example.Bank.model.Account;

@Service
public class AccountService implements AccountRepository {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    

    public AccountService(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }



    @Override 
    public AccountDto createAccount (AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountJpaRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
public AccountDto getAccountById(Long id) {
    Account account = accountJpaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    return AccountMapper.mapToAccountDto(account);
}
@Override
public List<AccountDto> getAllAccounts() {
    List<Account> accounts = accountJpaRepository.findAll();
    return AccountMapper.mapToAccountDtoList(accounts);
}
@Override
public AccountDto depositAmount(Long id , double amount){
    Account account = accountJpaRepository
                      .findById(id)
                      .orElseThrow(()-> new  RuntimeException("Account now found with this id " + id));
     double totalBalance = account.getBalance() + amount ;
     account.setBalance(totalBalance);
     Account account2 = accountJpaRepository.save(account);
     return AccountMapper.mapToAccountDto(account2);
    
}

@Override
public AccountDto withdrawAmount(Long id, double amount) throws Exception{
    Account account = accountJpaRepository
                      .findById(id)
                      .orElseThrow(()-> new RuntimeException("Account not found woth this id "+ id));
    double curentBalance = account.getBalance();
    if(curentBalance >= amount){
        account.setBalance( curentBalance - amount);
        Account account2 = accountJpaRepository.save(account);
        return AccountMapper.mapToAccountDto(account2);
    }
    else{
        throw new Exception("Insufficient funds for withdrawal. Current balance: " + curentBalance);
    }
}

@Override
public void deleteAccount(Long id) {
    if (!accountJpaRepository.existsById(id)) {
        throw new RuntimeException("Account not found with this id " + id);
    }
    accountJpaRepository.deleteById(id);
    
    System.out.println("Account Deleted Successfully....");
}

}
