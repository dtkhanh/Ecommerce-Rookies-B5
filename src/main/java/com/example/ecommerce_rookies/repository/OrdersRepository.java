package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
