package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
