package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<OrdersModel, Long> {

}

