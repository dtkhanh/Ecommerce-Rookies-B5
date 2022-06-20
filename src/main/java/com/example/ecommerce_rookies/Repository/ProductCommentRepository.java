package com.example.ecommerce_rookies.Repository;

import com.example.ecommerce_rookies.Models.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
}
