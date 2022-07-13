package com.example.ecommerce_rookies.services.impl;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


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
    public  Optional<Product> getProductById(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new NotFoundProductByCategory.NotFoundProduct(id);
        }
        return productRepository.findById(id);
    }

    @Override
    public Product getProductByName(String name){
        return productRepository.findProductByTitle(name);
    }

    @Override
    public void deleteProductByCategoryID(Long cid) {
        if (productRepository.findById(cid).isEmpty()) {
            throw new NotFoundProductByCategory.NotFoundProduct(cid);
        }
        productRepository.deleteAllByCategory_Id(cid);
    }

    @Override
    public ResponseEntity<?> deleteProductById(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new NotFoundProductByCategory.NotFoundProduct(id);
        }
        productRepository.deleteAllById(id);
        return ResponseEntity.ok().body(String.format("Delete product successfully"));
    }


    public Product convertProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        List<String> image = productDTO.getImage();
        if(!image.isEmpty())
            product.setImageproduct(image.get(0));
        product.setDescription(productDTO.getDescription());
        product.setTitle(productDTO.getName());
        product.setCreatedate(LocalDate.now());
        product.setUpdatedate(null);
        Optional<Category> category = categoryService.getCategoryId(Long.valueOf(productDTO.getCategoryid()));
        product.setCategory(category.get());
        return product;
    }
    @Override
    public ProductDTO convertProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategoryid(product.getCategory().getCategoryname());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(Collections.singletonList(product.getImageproduct()));
        productDTO.setName(product.getTitle());
        productDTO.setRating(product.getRatting());

        return productDTO;
    }

    @Override
    public List<Product> top5ProductRatting(){
        List<Product> list = productRepository.SortProductByRatting();
        List<Product> newlist = new ArrayList<>();
        if(list.size()>5) {
            for (int i = 0; i < 5; i++) {
                newlist.add(list.get(i));
            }
            return newlist;
        }
        return list;

    }

    @Override
    public ResponseEntity<?> updateProduct(long id, ProductDTO productDTO) {
//        if (productRepository.findById(id).isEmpty()) {
//            throw new NotFoundProductByCategory.NotFoundProduct(id);
        Optional<Category> category = categoryService.getCategoryId(Long.valueOf(productDTO.getCategoryid()));
        if(category.isEmpty())
            throw new NotFoundCategory(Long.valueOf(productDTO.getCategoryid()));
        Product prd =productRepository.findById(id).get();
        if(productRepository.findById(id).isEmpty())
            throw  new NotFoundProductByCategory.NotFoundProduct(id);
        prd.setTitle(productDTO.getName());
        List<String> image = productDTO.getImage();
        if(!image.isEmpty())
            prd.setImageproduct(image.get(0));
        prd.setDescription(productDTO.getDescription());
        prd.setTitle(productDTO.getName());
        prd.setUpdatedate(LocalDate.now());
        prd.setRatting(0);

        prd.setCategory(category.get());
        productRepository.save(prd);
        return ResponseEntity.ok(new MessageResponse("Product update successfully"));


    }


}
