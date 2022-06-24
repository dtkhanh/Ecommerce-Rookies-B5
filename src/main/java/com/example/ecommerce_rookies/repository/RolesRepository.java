package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
