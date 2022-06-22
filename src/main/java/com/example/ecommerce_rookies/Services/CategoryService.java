package com.example.ecommerce_rookies.Services;

import com.example.ecommerce_rookies.ModelDTO.CategoryDTO;
import com.example.ecommerce_rookies.Models.Category;
import org.springframework.data.domain.Example;

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

    Category getCategoryByName(String name);
}
