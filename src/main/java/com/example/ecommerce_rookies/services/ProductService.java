package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void delete(Product product);

    List<Product> findAll();

    Product createProduct(Product product);

    Optional<Product> getProductById(Long id);

    Product getProductByName(String name);

    void deleteProductByCategoryID(Long cid);

    ResponseEntity<?> deleteProductById(Long id);


    Product convertProduct(ProductDTO productDTO);


    ProductDTO convertProductDTO(Product product);

    List<Product> top5ProductRatting();

    ResponseEntity<?> updateProduct(long id, ProductDTO productDTO);


}
