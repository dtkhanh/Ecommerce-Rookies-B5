package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.modelDTO.AccountDto;
import com.example.ecommerce_rookies.modelDTO.SetActivity;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Infomation;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;


    private final InfomationRepository infomationRepository;
    private final ModelMapper modelMapper;
     @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, InfomationRepository infomationRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.infomationRepository = infomationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Account> getAccountList() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountId(Long id) {
        if(accountRepository.findById(id).isEmpty())
            throw new NotFoundAccount(id);
        return accountRepository.findById(id);
    }

    @Override
    public ResponseEntity<?> deleteAccountId(Long id){
        Optional<Account> account = Optional.of(accountRepository.getReferenceById(id));
        if(account.isEmpty())
            throw new NotFoundAccount(id);
        infomationRepository.deleteAllByAccount_Id(id);
        accountRepository.deleteById(id);
        return ResponseEntity.ok().body("User delete successfully!");
    }

    @Override
    public List<AccountDto> convertListDTO(List<Account> accountList){
        List<AccountDto> list = new ArrayList<>();
        for (Account account : accountList) {
            list.add(convertDTO(account));
        }
        return list;
    }

    @Override
    public AccountDto convertDTO(Account account){
        Optional<Infomation> infomation1 = infomationRepository.findById(account.getId());
        AccountDto accountDto =  new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUsername(infomation1.get().getUsername());
        accountDto.setPassword(account.getPassword());
        Optional<Infomation> infomation = infomationRepository.findById(account.getId());
        accountDto.setAddress(infomation.get().getAddress());
        accountDto.setEmail(infomation.get().getEmail());
        accountDto.setAvatar(infomation.get().getAvatar());
        accountDto.setPhone(infomation.get().getPhone());
        accountDto.setRole(account.getRoles().getRolename());
        accountDto.setCheck_activity(account.isCheck_Account());
        return accountDto;
    }

    @Override
    public ResponseEntity<?> updateAccount(long id, AccountDto accountDto) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty())
            throw  new NotFoundAccount(id);
        Infomation infomation = infomationRepository.getReferenceById(id);
        infomation.setUsername(accountDto.getUsername());
        infomation.setAddress(accountDto.getAddress());
        infomation.setEmail(accountDto.getEmail());
        infomation.setAvatar(accountDto.getAvatar());
        infomation.setPhone(accountDto.getPhone());
        infomation.setAccount(accountRepository.save(account.get()));
        infomationRepository.save(infomation);
        accountRepository.save(account.get());

        return ResponseEntity.ok().body("User update successfully!");
    }

    @Override
    public ResponseEntity<?> updateActivityAccount(long id, SetActivity setActivity) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty() )
            throw  new NotFoundAccount(id);
        if(Objects.equals(setActivity.getSet_activity(), "0")) {
            account.get().setCheck_Account(false);
        }else{
            account.get().setCheck_Account(true);
        }
        accountRepository.save(account.get());
        return ResponseEntity.ok().body("User update activity successfully!");

    }

}
