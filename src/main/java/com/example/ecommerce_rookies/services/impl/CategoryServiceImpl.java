package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Optional<Category> getCategoryId(Long id) { return categoryRepository.findById(id); }
    // READ
    @Override
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }
    // DELETE
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    @Override
    public Category updateCategory(long id, Category category) {
        Category category1 = categoryRepository.getOne(id);
        category1.setCategoryname(category.getCategoryname());
        return categoryRepository.save(category1);
    }
    @Override
    public Category getReferenceById(Long id) {
        return categoryRepository.getReferenceById(id);    }



}
