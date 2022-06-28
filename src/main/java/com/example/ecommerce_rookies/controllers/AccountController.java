package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.modelDTO.AccountDto;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private InfomationRepository infomationRepository;


    @GetMapping(value="")
    public List<Account> readAccounts() {
        return accountService.getAccountList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountbyId(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountId(id);
        return ResponseEntity.ok().body(account.get());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteAccountbyId(@PathVariable Long id){
        Optional<Account> account = accountService.getAccountId(id);
        accountService.deleteAccountId(id);
        infomationRepository.deleteById(id);
        return  ResponseEntity.ok(new MessageResponse("User delete successfully!" + account.get().getUsername() ) );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto){
        Optional<Account> account = accountService.getAccountId(id);
        accountService.updateAccount(id,accountDto);
        return ResponseEntity.ok(new MessageResponse("User update successfully!" + account.get().getUsername() ) );
    }

}