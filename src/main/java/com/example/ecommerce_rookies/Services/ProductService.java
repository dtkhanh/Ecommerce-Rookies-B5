package com.example.ecommerce_rookies.Services;

import com.example.ecommerce_rookies.ModelDTO.ProductDTO;
import com.example.ecommerce_rookies.Models.Category;
import com.example.ecommerce_rookies.Models.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    void delete(Product product);

    List<Product> findAll();

    Product createProduct(Product product);

    Product convertProduct(ProductDTO productDTO);


    Product updateProduct(long id, ProductDTO productDTO);
}
