package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAccountList();

    Optional<Account> getAccountId(Long id);
}
