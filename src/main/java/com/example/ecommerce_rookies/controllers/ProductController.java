package com.example.ecommerce_rookies.controllers;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Gallery;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.models.ProductComment;
import com.example.ecommerce_rookies.payload.response.MessageResponse;
import com.example.ecommerce_rookies.repository.GalleryRepository;
import com.example.ecommerce_rookies.repository.OrderDetailsRepository;
import com.example.ecommerce_rookies.repository.ProductCommentRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductCommentRepository productCommentRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<?> createCategory( @RequestBody ProductDTO prd){
       Product product = productService.createProduct(productService.convertProduct(prd));
       List<String> image = prd.getImage();
        if(image.size()>1){
            for(int i=1; i<image.size();i++){
                Gallery gallery = new Gallery();
                gallery.setProduct(product);
                gallery.setImage(image.get(i));
                galleryRepository.save(gallery);
            }
        }
        return  ResponseEntity.ok().body(product);

    }
    @GetMapping("")
    public ResponseEntity<?> GetProducts(){

        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name){
        Product product = productService.getProductByName(name);
        if(product == null)
            throw new NotFoundProductByCategory.NotFoundProduct(product.getId());
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductbyId(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.ok().body(productService.convertProductDTO(product.get()));
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

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(new MessageResponse("Product update successfully"));
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteProductId(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        Set<ProductComment> list = product.get().getProductComments();
        orderDetailsRepository.deleteAllByProductId(id);
        productRepository.deleteAllById(id);
//        if(!list.isEmpty()) {
//            for (ProductComment productComment : list) {
//                productCommentRepository.deleteAllById(productComment.getId());
//            }
//        }
//        galleryRepository.deleteGalleryByProduct_Id(id);
//        productService.deleteProductById(id);
        return ResponseEntity.ok().body(String.format("Delete product successfully"));
    }

    @GetMapping("/sort")
    public ResponseEntity<?> top5Product(){
        return ResponseEntity.ok().body(productService.top5ProductRatting());
    }


}
