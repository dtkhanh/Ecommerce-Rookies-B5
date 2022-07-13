package com.example.ecommerce_rookies.TestService;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.modelDTO.AccountDto;
import com.example.ecommerce_rookies.modelDTO.CategoryDTO;
import com.example.ecommerce_rookies.modelDTO.SetActivity;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Infomation;
import com.example.ecommerce_rookies.models.Roles;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.services.impl.AccountServiceImpl;
import com.example.ecommerce_rookies.services.impl.CategoryServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class AccountServiceTest {

    private AccountServiceImpl accountServiceImpl;
    private AccountRepository accountRepository;
    private  InfomationRepository infomationRepository;
    private ModelMapper modelMapper;
    private Account account;
    private AccountDto accountDto;

    public AccountServiceTest() {
    }


    @BeforeEach
    public void beforeEach() {

        accountRepository = mock(AccountRepository.class);
        infomationRepository = mock(InfomationRepository.class);
        modelMapper = mock(ModelMapper.class);
        accountServiceImpl = new AccountServiceImpl(accountRepository,infomationRepository, modelMapper);
        accountDto = mock(AccountDto.class);
        account = mock(Account.class);

    }


    @Test
    public  void get_Account_By_Id(){
        Long id = 1L;
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        accountServiceImpl.getAccountId(id);
        assertEquals(accountServiceImpl.getAccountId(id),Optional.of(account));
        verify(accountRepository, times(4)).findById(id);
    }

    @Test
    public void get_All_Account(){
        List<Account> accounts = new ArrayList<>();
        accounts.add(mock(Account.class));
        accounts.add(mock(Account.class));
        when(accountRepository.findAll()).thenReturn(accounts);
        assertEquals(accountServiceImpl.getAccountList().size(), accounts.size());
    }
    @Test
    public void Test_updateAccount_NotException(){
        Roles roles = mock(Roles.class);
        Long id = 1L;
        Account account1 = new Account(id,"abc","khanhzzzz",roles,true,null,null,null,null);
        account = account1;
        when(accountRepository.findById(id)).thenReturn(Optional.empty());
        when(infomationRepository.findById(id)).thenReturn(Optional.empty());
        NotFoundAccount exception = Assertions.assertThrows(NotFoundAccount.class,
                () -> accountServiceImpl.updateAccount(1L,accountDto));
        assertThat(exception.getMessage(), is("Can not find account with id: 1"));
    }
    @Test
    public void updateUserInfo() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        Infomation infomation = new Infomation("khanh","abc@gmail","123...","tmage1","12345",account);
        AccountDto accountDto1= new AccountDto(1L,"khanh","khanh123","QuangNam","113","123@gmail","image1");
        when(infomationRepository.getReferenceById(1L)).thenReturn((infomation));
        when(accountRepository.findById(infomation.getAccount().getId())).thenReturn(Optional.of(account));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        infomation.setUsername(accountDto1.getUsername());
        infomation.setAddress(accountDto1.getAddress());
        infomation.setEmail(accountDto1.getEmail());
        infomation.setAvatar(accountDto1.getAvatar());
        infomation.setPhone(accountDto1.getPhone());
        infomation.setAccount(accountRepository.save(account));
        when(infomationRepository.save(infomation)).thenReturn(infomation);
        when(accountRepository.save(account)).thenReturn(account);
        System.out.println(infomation.getAddress());
        ResponseEntity<?> result = accountServiceImpl.updateAccount(1L,accountDto1);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }
    @Test
    public void updateActivity_Account_NotFound(){
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        SetActivity setActivity = mock(SetActivity.class);
        NotFoundAccount exception = Assertions.assertThrows(NotFoundAccount.class,
                () -> accountServiceImpl.updateActivityAccount(1L,setActivity));
        assertThat(exception.getMessage(), is("Can not find account with id: 1"));
    }
    @Test
    public void updateActivity_Account_False(){
        SetActivity setActivity = new SetActivity("0");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        account.setCheck_Account(false);
        when(accountRepository.save(account)).thenReturn(account);
        ResponseEntity<?> result = accountServiceImpl.updateActivityAccount(1L,setActivity);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }
    @Test
    public void updateActivity_Account_True(){
        SetActivity setActivity = new SetActivity("1");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        account.setCheck_Account(true);
        when(accountRepository.save(account)).thenReturn(account);
        ResponseEntity<?> result = accountServiceImpl.updateActivityAccount(1L,setActivity);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }
    @Test
    public void Test_Convert_DTO(){
        Roles roles = mock(Roles.class);
        Account account1 = new Account(1L,"abcde","khanh123",roles,true,null,null,null,null);

    }








}
