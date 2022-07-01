package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {


    @Transactional
    @Modifying
    @Query("delete from OrderDetails o where o.id = ?1")
    void deleteAllById(Long id);
}
