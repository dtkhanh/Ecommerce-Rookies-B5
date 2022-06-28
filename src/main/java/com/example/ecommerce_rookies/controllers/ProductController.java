package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("")
    public  ResponseEntity<Product> createCategory( @RequestBody ProductDTO prd){
        productService.createProduct(productService.convertProduct(prd));
       // return ResponseEntity.ok().body(prd);
        return new ResponseEntity<>(productService.createProduct(productService.convertProduct(prd)), HttpStatus.CREATED);

    }
    @GetMapping("")
    public List<Product> GetProducts(){
        return productService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(new MessageResponse("Product update successfully"));
    }

}
