package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}