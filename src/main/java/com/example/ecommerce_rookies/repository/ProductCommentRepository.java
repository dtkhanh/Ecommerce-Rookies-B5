package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

 @Transactional
 @Modifying
 @Query("delete from ProductComment p where p.id = ?1")
 void deleteAllById(Long id);

}
