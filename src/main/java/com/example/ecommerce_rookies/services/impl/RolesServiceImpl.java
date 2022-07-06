package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.models.Roles;
import com.example.ecommerce_rookies.repository.RolesRepository;
import com.example.ecommerce_rookies.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
    private final RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Roles> getRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Optional<Roles> getRoleId(Long id) { return rolesRepository.findById(id); }


    @Override
    public Roles createRoles(Roles emp) {
        return rolesRepository.save(emp);
    }



}

