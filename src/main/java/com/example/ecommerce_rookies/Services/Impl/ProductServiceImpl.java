package com.example.ecommerce_rookies.Services.Impl;

import com.example.ecommerce_rookies.ModelDTO.ProductDTO;
import com.example.ecommerce_rookies.Models.Category;
import com.example.ecommerce_rookies.Models.Product;
import com.example.ecommerce_rookies.Repository.ProductRepository;
import com.example.ecommerce_rookies.Services.CategoryService;
import com.example.ecommerce_rookies.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product convertProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setPrice(productDTO.getPrice());
        product.setImageproduct(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setTitle(productDTO.getName());
        product.setCreatedate(LocalDate.now());
        product.setUpdatedate(null);
        Optional<Category> category = categoryService.getCategoryId(Long.valueOf(productDTO.getCategoryid()));
        product.setCategory(category.get());
        return product;
    }

    @Override
    public Product updateProduct(long id, ProductDTO productDTO) {
        Product prd = productRepository.getOne(id);
        if(prd==null)
            return null;
        prd.setPrice(productDTO.getPrice());
        prd.setImageproduct(productDTO.getImage());
        prd.setDescription(productDTO.getDescription());
        prd.setTitle(productDTO.getName());
        prd.setCreatedate(LocalDate.now());
        prd.setUpdatedate(null);
        Optional<Category> category = categoryService.getCategoryId(Long.valueOf(productDTO.getCategoryid()));
        prd.setCategory(category.get());
        return productRepository.save(prd);
    }
}
