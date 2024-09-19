package com.example.Bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bank.model.Account;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

}
