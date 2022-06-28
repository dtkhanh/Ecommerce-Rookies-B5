package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping(value = "" )
    public ResponseEntity<?> createCategory(@RequestBody Category ctg){
       categoryService.createCategory(ctg);
       return ResponseEntity.ok(new MessageResponse("Category registered successfully!"));
    }
    @GetMapping(value="")
    public List<Category> readCategorys() {
        return categoryService.getCategory();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok(new MessageResponse("Category update successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategorybyId(@PathVariable Long id) {
        Optional<Category> ca = categoryService.getCategoryId(id);
        return ResponseEntity.ok().body(ca.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Category category = categoryService.getReferenceById(id);
        if(category==null)
            return ResponseEntity.ok(new MessageResponse("Category is null"));
        else{
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body(category);
        }
    }


}
