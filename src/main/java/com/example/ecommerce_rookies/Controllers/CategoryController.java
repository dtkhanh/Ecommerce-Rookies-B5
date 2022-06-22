package com.example.ecommerce_rookies.Controllers;

import com.example.ecommerce_rookies.ModelDTO.CategoryDTO;
import com.example.ecommerce_rookies.Models.Category;
import com.example.ecommerce_rookies.Repository.CategoryRepository;
import com.example.ecommerce_rookies.Services.CategoryService;
import com.example.ecommerce_rookies.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private ProductService productService;

    @RequestMapping(value = "/insert" , method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category ctg){
        return categoryService.createCategory(ctg);
    }
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Category> readEmployees() {
        return categoryService.getCategory();
    }
    @PutMapping("/{id}/update")
    public Category updateProduct(@PathVariable Long id,@RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteCategory(@PathVariable Long id){
        Category category = categoryService.getReferenceById(id);
        if(category==null)
            return ;
        else{
            categoryService.deleteCategory(id);
        }
    }

}
