package com.example.ecommerce_rookies.Repository;

import com.example.ecommerce_rookies.Models.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//Add , update , delete
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoriesByCategoryname (String categoryname);

}
