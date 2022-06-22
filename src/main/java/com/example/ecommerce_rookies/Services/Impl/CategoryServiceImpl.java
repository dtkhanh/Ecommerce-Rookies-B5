package com.example.ecommerce_rookies.Services.Impl;

import com.example.ecommerce_rookies.Models.Category;
import com.example.ecommerce_rookies.Repository.CategoryRepository;
import com.example.ecommerce_rookies.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category emp) {
        return categoryRepository.save(emp);
    }

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
        return categoryRepository.getReferenceById(aLong);
    }


}
