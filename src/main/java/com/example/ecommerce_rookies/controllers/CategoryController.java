package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.repository.GalleryRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private ProductService productService;

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
        if(categoryService.getCategoryId(id).isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(id);
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok().body(String.format("update category successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategorybyId(@PathVariable Long id) {
        Optional<Category> ca = categoryService.getCategoryId(id);
        if(ca.isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(id);
        return ResponseEntity.ok().body(categoryService.getReferenceById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Optional<Category> ca = categoryService.getCategoryId(id);
        if (!ca.isPresent()) {
            throw new NotFoundCategory(id);
        }
        Set<Product> list = ca.get().getProducts();
        if(!list.isEmpty()) {
            for (Product product : list) {
                if(!product.getGallery().isEmpty())
                    galleryRepository.deleteGalleryByProduct_Id(product.getId());
            }
        }
        productService.deleteProductByCategoryID(id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(String.format("Delete successfully!"));
    }


}
