package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
