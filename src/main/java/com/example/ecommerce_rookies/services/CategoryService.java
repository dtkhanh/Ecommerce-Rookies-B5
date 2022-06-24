package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);

    Optional<Category> getCategoryId(Long id);

    // READ
    List<Category> getCategory();
    //UPDATE
    Category updateCategory(long id, Category category);
    // DELETE
    void deleteCategory(Long empId);

    Category getReferenceById(Long aLong);

}
