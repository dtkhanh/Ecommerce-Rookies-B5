package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdersModelRepository extends JpaRepository<OrdersModel, Long> {
}
