package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "" , method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category ctg){
        return categoryService.createCategory(ctg);
    }
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Category> readEmployees() {
        return categoryService.getCategory();
    }
    @PutMapping("/{id}")
    public Category updateProduct(@PathVariable Long id,@RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<Category> ca = categoryService.getCategoryId(id);
        return ResponseEntity.ok().body(ca.get());
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        Category category = categoryService.getReferenceById(id);
        if(category==null)
            return ;
        else{
            categoryService.deleteCategory(id);
        }
    }


}
