package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.jwt.JwtUtils;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Infomation;
import com.example.ecommerce_rookies.models.Roles;
import com.example.ecommerce_rookies.payload.request.LoginRequest;
import com.example.ecommerce_rookies.payload.request.SignupRequest;
import com.example.ecommerce_rookies.payload.response.JwtResponse;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.repository.RolesRepository;
import com.example.ecommerce_rookies.services.RolesService;
import com.example.ecommerce_rookies.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

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
            user.setRoles(roles.get());
            userRepository.save(user);
            Infomation info = new Infomation(signUpRequest.getUsername(), signUpRequest.getEmail(), null, null, null, userRepository.save(user));
            infomationRepository.save(info);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!" + roles.get().getRolename()));
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println("khanh" );
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Account accountLogin = userRepository.findByUsername(loginRequest.getUsername()).get();
        String roles = accountLogin.getRoles().getRolename();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));

    }
}