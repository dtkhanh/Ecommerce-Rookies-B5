package com.example.ecommerce_rookies.Repository;

import com.example.ecommerce_rookies.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
