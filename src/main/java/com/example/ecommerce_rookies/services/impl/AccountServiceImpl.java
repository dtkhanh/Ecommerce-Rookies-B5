package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.modelDTO.AccountDto;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Infomation;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    private InfomationRepository infomationRepository;

    @Override
    public List<Account> getAccountList() { return accountRepository.findAll(); }

    @Override
    public Optional<Account> getAccountId(Long id) { return accountRepository.findById(id); }

    @Override
    public void deleteAccountId(Long id){
        accountRepository.deleteById(id);
    }

    @Override
    public Account updateAccount(long id, AccountDto accountDto) {
        Account account = accountRepository.getOne(id);
        Infomation infomation = infomationRepository.getOne(id);
        Account user = new Account(accountDto.getUsername(),
                encoder.encode(accountDto.getPassword()));
        account.setUsername(user.getUsername());
        account.setPassword(user.getPassword());
        accountRepository.save(account);
        infomation.setUsername(accountDto.getUsername());
        infomation.setAddress(accountDto.getAddress());
        infomation.setEmail(accountDto.getEmail());
        infomation.setAvatar(accountDto.getAvatar());
        infomation.setPhone(accountDto.getPhone());
        infomation.setAccount(accountRepository.save(account));
        infomationRepository.save(infomation);
        return  accountRepository.save(account);
    }

}
