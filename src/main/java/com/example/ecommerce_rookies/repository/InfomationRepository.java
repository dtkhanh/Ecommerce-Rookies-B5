package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Infomation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfomationRepository extends JpaRepository<Infomation, Long> {

    Boolean existsByEmail(String email);

}
