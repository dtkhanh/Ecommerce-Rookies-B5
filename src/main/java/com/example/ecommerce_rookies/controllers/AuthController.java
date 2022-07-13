package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.jwt.JwtUtils;
import com.example.ecommerce_rookies.models.*;
import com.example.ecommerce_rookies.payload.request.LoginRequest;
import com.example.ecommerce_rookies.payload.request.SignupRequest;
import com.example.ecommerce_rookies.payload.response.JwtResponse;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.repository.CartmodelRepository;
import com.example.ecommerce_rookies.repository.RolesRepository;
import com.example.ecommerce_rookies.services.AccountService;
import com.example.ecommerce_rookies.services.RolesService;
import com.example.ecommerce_rookies.services.impl.UserDetailsImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CartmodelRepository cartmodelRepository;

    @Autowired
    RolesService rolesService;

    @Autowired
    InfomationRepository infomationRepository;

    @Autowired
    AccountRepository userRepository;

    @Autowired
    RolesRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private  AccountRepository accountRepository;


    public AuthController (AuthenticationManager authenticationManager, AccountRepository userRepository,
                           RolesRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (infomationRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        else {
            Account user = new Account(encoder.encode(signUpRequest.getPassword()), signUpRequest.getName());
            String strRoles = signUpRequest.getRole();
            if (strRoles == null)
                strRoles = "1";
            Optional<Roles> roles = rolesService.getRoleId(Long.valueOf(strRoles));
            Optional<Roles> optionalRole = roleRepository.findById(Long.parseLong(strRoles));
            user.setCheck_Account(true);
            user.setRoles(roles.get());
            userRepository.save(user);
            Infomation info = new Infomation(signUpRequest.getUsername(), signUpRequest.getEmail(), null, null, null, userRepository.save(user));
                Cartmodel cartmodel = new Cartmodel();
                cartmodel.setTotal(0.0F);
                cartmodel.setOrderdate(LocalDate.now());
                cartmodel.setAccount(user);
                cartmodel.setOrderDetails(null);
                cartmodel.setOrdersModels(null);
                cartmodelRepository.save(cartmodel);
            infomationRepository.save(info);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!" + roles.get().getRolename()));
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        List<Account> accounts = accountRepository.findAll();
        boolean check = true;
        for(Account account : accounts){
            Optional<Infomation> infomation = infomationRepository.findById(account.getId());
            System.out.println("hahahahaha" + infomation.get().getUsername());

            if(Objects.equals(account.getUsername(), loginRequest.getUsername())){
                System.out.println("hahahahaha1");
                if(!account.isCheck_Account()) {
                    check = false;
                    System.out.println("hahahahaha");
                }

            }
        }
        if(check) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateJwtToken(authentication);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles.get(0)));
        }
        throw new  NotFoundAccount.ExitsAccount();
    }
}