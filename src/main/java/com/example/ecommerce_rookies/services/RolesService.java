package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.models.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    List<Roles> getRoles();

    Optional<Roles> getRoleId(Long id);

    Roles createRoles(Roles emp);

}
