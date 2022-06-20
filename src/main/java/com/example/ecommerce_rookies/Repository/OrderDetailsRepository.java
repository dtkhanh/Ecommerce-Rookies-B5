package com.example.ecommerce_rookies.Repository;

import com.example.ecommerce_rookies.Models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
