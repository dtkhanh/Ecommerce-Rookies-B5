package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.jwt.JwtUtils;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Infomation;
import com.example.ecommerce_rookies.models.Roles;
import com.example.ecommerce_rookies.payload.request.SignupRequest;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.InfomationRepository;
import com.example.ecommerce_rookies.repository.RolesRepository;
import com.example.ecommerce_rookies.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
        Account user = new Account(signUpRequest.getName(),
                encoder.encode(signUpRequest.getPassword()));
        String strRoles = signUpRequest.getRole();
        if(strRoles == null)
            strRoles = "1";
        Optional<Roles> roles = rolesService.getRoleId(Long.valueOf(strRoles));
        Optional<Roles> optionalRole= roleRepository.findById(Long.parseLong(strRoles));
        user.setRoles(roles.get());
        userRepository.save(user);


        Infomation info = new Infomation(signUpRequest.getUsername(),signUpRequest.getEmail(),null,null,null,userRepository.save(user));
        infomationRepository.save(info);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!" + roles.get().getRolename()));
    }
}