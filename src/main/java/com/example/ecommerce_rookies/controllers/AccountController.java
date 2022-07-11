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
        if(account.isEmpty())
            throw new NotFoundAccount(id);
        return ResponseEntity.ok().body(accountService.convertDTO(account.get()));
    }

    @DeleteMapping("/admin/{id}")
    public  ResponseEntity<?> deleteAccountbyId(@PathVariable Long id){
        Optional<Account> account = accountService.getAccountId(id);
        if(account.isEmpty())
            throw new NotFoundAccount(id);
        infomationRepository.deleteAllByAccount_Id(id);
        accountService.deleteAccountId(id);
        return ResponseEntity.ok().body("User delete successfully!");
    }
    @PutMapping("/set_activity/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody SetActivity setActivity){
        Optional<Account> account = accountService.getAccountId(id);
        if(account.isEmpty())
            throw  new NotFoundAccount(id);
        accountService.updateActivityAccount(id,setActivity);
        return ResponseEntity.ok().body("User update activity successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivityAccount(@PathVariable Long id, @RequestBody AccountDto accountDto){
        Optional<Account> account = accountService.getAccountId(id);
        if(account.isEmpty())
            throw  new NotFoundAccount(id);
        accountService.updateAccount(id,accountDto);
        return ResponseEntity.ok().body("User update successfully!");
    }

}