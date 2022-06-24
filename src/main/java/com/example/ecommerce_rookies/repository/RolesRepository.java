package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.RoleName;
import com.example.ecommerce_rookies.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findById(RoleName aLong);
}
