package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void delete(Product product);

    List<Product> findAll();

    Product createProduct(Product product);

    Optional<Product> getProductById(Long id);

    void deleteProductById(Long id);


    Product convertProduct(ProductDTO productDTO);


    Product updateProduct(long id, ProductDTO productDTO);
}
