package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.jwt.JwtUtils;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Roles;
import com.example.ecommerce_rookies.payload.request.SignupRequest;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.AccountRepository;
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
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        Account user = new Account(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        String strRoles = signUpRequest.getRole();
//        Optional<Category> category = categoryService.getCategoryId(Long.valueOf(productDTO.getCategoryid()));
        if(strRoles == null)
            strRoles = "1";
        Optional<Roles> roles = rolesService.getRoleId(Long.valueOf(strRoles));
        Optional<Roles> optionalRole= roleRepository.findById(Long.parseLong(strRoles));
        user.setRoles(roles.get());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!" + roles.get().getRolename()));
//
//        if (strRoles == null) {
//            Roles userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role.toLowerCase()) {
//                    case "admin":
//                        Roles adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    default:
//                        Roles userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);


    }
}