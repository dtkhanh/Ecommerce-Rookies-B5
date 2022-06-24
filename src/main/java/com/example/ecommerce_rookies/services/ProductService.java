package com.example.ecommerce_rookies.services;

import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Product;

import java.util.List;

public interface ProductService {

    void delete(Product product);

    List<Product> findAll();

    Product createProduct(Product product);

    Product convertProduct(ProductDTO productDTO);


    Product updateProduct(long id, ProductDTO productDTO);
}
