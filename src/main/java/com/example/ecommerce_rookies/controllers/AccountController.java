package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Account> readAccount() {
        return accountService.getAccountList();
    }

}