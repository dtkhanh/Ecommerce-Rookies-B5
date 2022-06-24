package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
}
