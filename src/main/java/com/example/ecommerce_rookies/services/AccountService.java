package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.AccountDto;
import com.example.ecommerce_rookies.modelDTO.SetActivity;
import com.example.ecommerce_rookies.models.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAccountList();

    Optional<Account> getAccountId(Long id);

    ResponseEntity<?> deleteAccountId(Long id);

    List<AccountDto> convertListDTO(List<Account> accountList);

    AccountDto convertDTO(Account account);

    ResponseEntity<?> updateAccount(long id, AccountDto accountDto);

    ResponseEntity<?> updateActivityAccount(long id, SetActivity setActivity);
}
