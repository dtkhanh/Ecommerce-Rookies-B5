package com.example.ecommerce_rookies.controllers;


import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Roles;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @PostMapping(value = "")
    public Roles createRoles(@RequestBody Roles roles){
        return rolesService.createRoles(roles);
    }
    @GetMapping(value="")
    public List<Roles> readRoles() {
        return rolesService.getRoles();
    }
}
