package com.example.ecommerce_rookies.Repository;

import com.example.ecommerce_rookies.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
