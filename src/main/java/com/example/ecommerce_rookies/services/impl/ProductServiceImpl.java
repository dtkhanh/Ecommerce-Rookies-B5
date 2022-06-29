package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public  Optional<Product> getProductById(Long id) { return productRepository.findById(id);}

    @Override
    public void deleteProductByCategoryID(Long cid) { productRepository.deleteAllByCategory_Id(cid); }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteAllById(id);
    }


    public Product convertProduct(ProductDTO productDTO) {
        Product product = new Product();
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
