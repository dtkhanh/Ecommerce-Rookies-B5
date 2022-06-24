package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.BanUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanUserRepository extends JpaRepository<BanUser, Long> {
}
