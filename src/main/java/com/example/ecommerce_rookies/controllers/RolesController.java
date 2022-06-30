package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Roles;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @PostMapping(value = "")
    public ResponseEntity<?> createRoles(@RequestBody Roles roles){
        rolesService.createRoles(roles);
        return ResponseEntity.ok(new MessageResponse("Roles update successfully"));
    }
    @GetMapping(value="")
    public ResponseEntity<?> readRoles() {
        return ResponseEntity.ok().body(rolesService.getRoles());
    }
}
