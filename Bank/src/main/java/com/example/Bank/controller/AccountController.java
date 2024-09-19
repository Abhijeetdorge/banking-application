package com.example.Bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Bank.dto.AccountDto;
import com.example.Bank.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account REST API
    @PostMapping("/add")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // get All accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // get account rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long Id){
        AccountDto accountDto = accountService.getAccountById(Id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit amount api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id,
                                                    @RequestBody Map<String , Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.depositAmount(id, amount);
        return ResponseEntity.ok(accountDto);

    }

    //withdraw money rest api

    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id,
                                                    @RequestBody Map<String, Double> request) throws Exception{
          
        Double requestAmount = request.get("amount");

        if (requestAmount == null || requestAmount <= 0) {
            throw new IllegalArgumentException("Invalid amount for withdrawal.");
        }
        AccountDto accountDto = accountService.withdrawAmount(id, requestAmount);
        
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }
}
