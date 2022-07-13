package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.modelDTO.AccountDto;
import com.example.ecommerce_rookies.modelDTO.SetActivity;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    private final InfomationRepository infomationRepository;

    public AccountController(AccountService accountService, InfomationRepository infomationRepository) {
        this.accountService = accountService;
        this.infomationRepository = infomationRepository;
    }


    @GetMapping("/admin")
    public ResponseEntity<?> readAccounts() {
        return ResponseEntity.ok().body(accountService.convertListDTO(accountService.getAccountList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")

    public ResponseEntity<?> getAccountbyId(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountId(id);
        return ResponseEntity.ok().body(accountService.convertDTO(account.get()));
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<?> deleteAccountbyId(@PathVariable Long id){
        return accountService.deleteAccountId(id);
    }
    @PutMapping("/set_activity/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody SetActivity setActivity){
        return accountService.updateActivityAccount(id,setActivity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateActivityAccount(@PathVariable Long id, @RequestBody AccountDto accountDto){
        return accountService.updateAccount(id,accountDto);
    }

}