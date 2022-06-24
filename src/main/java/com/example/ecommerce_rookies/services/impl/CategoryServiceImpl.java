package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category emp) {
        return categoryRepository.save(emp);
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
    public void deleteCategory(Long empId) {
        categoryRepository.deleteById(empId);
    }
    @Override
    public Category updateCategory(long id, Category category) {
        Category cte = categoryRepository.getOne(id);
        if(cte==null)
            return null;
        cte.setCategoryname(category.getCategoryname());
        return categoryRepository.save(cte);
    }
    @Override
    public Category getReferenceById(Long aLong) {
        return categoryRepository.getReferenceById(aLong);    }
    @Override
    public Category getCategoryByName(String name) { return categoryRepository.findCategoriesByCategoryname(name); }



}
