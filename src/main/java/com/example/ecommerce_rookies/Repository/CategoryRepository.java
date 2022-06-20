package com.example.ecommerce_rookies.Repository;

import com.example.ecommerce_rookies.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
