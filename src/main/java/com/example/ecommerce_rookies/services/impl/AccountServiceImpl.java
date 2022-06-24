package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccountList() { return accountRepository.findAll(); }


}
