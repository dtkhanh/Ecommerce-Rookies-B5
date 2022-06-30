package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying
    @Query("delete from Product p where p.category.id = ?1")
    void deleteAllByCategory_Id(Long id);

    @Transactional
    @Modifying
    @Query("delete from Product p where p.id = ?1")
    void deleteAllById(Long id);

    Product findProductByTitle(String name);

    @Transactional
    @Modifying
    @Query("SELECT e FROM Product e ORDER BY e.ratting DESC")
    List<Product> SortProductByRatting();



}
