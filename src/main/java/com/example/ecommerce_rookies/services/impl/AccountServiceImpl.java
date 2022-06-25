package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccountList() { return accountRepository.findAll(); }

    @Override
    public Optional<Account> getAccountId(Long id) { return accountRepository.findById(id); }

    @Override
    public void deleteAccountId(Long id){
        accountRepository.deleteById(id);
    }


}
