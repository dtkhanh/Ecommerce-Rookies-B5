package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Add , update , delete
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoriesByCategoryname (String categoryname);

}
