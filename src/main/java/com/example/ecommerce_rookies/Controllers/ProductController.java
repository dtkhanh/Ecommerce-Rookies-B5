package com.example.ecommerce_rookies.Controllers;

import com.example.ecommerce_rookies.Database.ResponseObject;
import com.example.ecommerce_rookies.ModelDTO.ProductDTO;
import com.example.ecommerce_rookies.Models.Category;
import com.example.ecommerce_rookies.Models.Product;
import com.example.ecommerce_rookies.Repository.ProductRepository;
import com.example.ecommerce_rookies.Services.CategoryService;
import com.example.ecommerce_rookies.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("")
    public  ResponseEntity<?> createCategory( @RequestBody(required = true) ProductDTO prd){
        productService.createProduct(productService.convertProduct(prd));
        return ResponseEntity.ok().body(prd);
    }
    @GetMapping("")
    public List<Product> GetProduct(){
        return productService.findAll();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

}
