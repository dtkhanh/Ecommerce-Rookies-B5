package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.ProductRepository;
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
@RequestMapping("/api/auth/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;
    @PostMapping("")
    public  ResponseEntity<?> createCategory( @RequestBody ProductDTO prd){
       Product product = productService.createProduct(productService.convertProduct(prd));
        return  ResponseEntity.ok().body(product);

    }
    @GetMapping("")
    public List<Product> GetProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductbyId(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            throw new NotFoundProductByCategory.NotFoundProduct(id);
        }
        return ResponseEntity.ok().body(product.get());
    }
    @GetMapping("/category/{category_id}")
    public ResponseEntity<?> adminGetProductByCategory(@PathVariable Long category_id) {
        if(categoryService.getCategoryId(category_id).isEmpty())
            return  ResponseEntity.ok().body(String.format("Could not find category with id:" + category_id));
        List<Product> listproduct = new ArrayList<>();
        List<Product> newlistproduct = new ArrayList<>();
            listproduct = productService.findAll();
            for (Product product : listproduct) {
                if (product.getCategory().getId() == category_id)
                    newlistproduct.add(product);
            }
            if(newlistproduct.isEmpty())
                throw  new NotFoundProductByCategory();
            else
                return ResponseEntity.ok().body((newlistproduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        Optional<Product> product = productService.getProductById(id);
        if(product.isEmpty())
            throw  new NotFoundProductByCategory.NotFoundProduct(id);
        else {
            productService.updateProduct(id, productDTO);
            return ResponseEntity.ok(new MessageResponse("Product update successfully"));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductId(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        if(product.isEmpty())
            throw new NotFoundProductByCategory.NotFoundProduct(id);
        else {
            productService.deleteProductById(id);
          return ResponseEntity.ok().body(String.format("Delete product successfully"));
        }
    }


}
