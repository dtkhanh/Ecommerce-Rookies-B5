package com.example.ecommerce_rookies.TestService;

import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.AccountDto;
import com.example.ecommerce_rookies.modelDTO.ProductDTO;
import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Infomation;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.AccountRepository;
import com.example.ecommerce_rookies.repository.ProductRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.impl.AccountServiceImpl;
import com.example.ecommerce_rookies.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.http.HttpStatus;

import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProductServiceTest {

    private  ProductRepository productRepository;

    private  CategoryService categoryService;

    private ProductServiceImpl productServiceImpl;

    private Product product;
    private ProductDTO productDTO;
    private Category category;

    @BeforeEach
    public void beforeEach() {
        productRepository = mock(ProductRepository.class);
        categoryService = mock(CategoryService.class);
        productServiceImpl = new ProductServiceImpl(productRepository, categoryService);
        product = mock(Product.class);
        productDTO = mock(ProductDTO.class);
        category = mock(Category.class);

    }
    @Test
    public void getAllProduct_ReturnList_True() {
        List<Product> list = new ArrayList<Product>();
        list.add(product);
        list.add(product);
        when(productRepository.findAll()).thenReturn(list);
        List<Product> result = productServiceImpl.findAll();
        assertThat(result.size(), is(list.size()));
    }
    @Test
    public void getProduct_ById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> result = productServiceImpl.getProductById(1L);
        assertThat(product, is(result.get()));
    }
    @Test
    public void getBook_ShouldThrowResourceNotFoundException_WhenBookIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        NotFoundProductByCategory.NotFoundProduct exception = Assertions.assertThrows(NotFoundProductByCategory.NotFoundProduct.class,
                () -> productServiceImpl.getProductById(1L));
        assertThat(exception.getMessage(), is("Can not find product with id1"));
    }
    @Test
    public void delete_Product(){
        when(productRepository.findById(100L)).thenReturn(Optional.of(product));
        ResponseEntity<?> result = productServiceImpl.deleteProductById(100L);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }
    @Test
    public void update_Product_Succesfully(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryService.getCategoryId(Long.valueOf("2"))).thenReturn(Optional.of(category));
        List<String> list = new ArrayList<>();
        ProductDTO productDTO1 = new ProductDTO("1","2",1,list,"2",0);
        ResponseEntity<?> result = productServiceImpl.updateProduct(1L, productDTO1);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));

    }

    @Test
    public void update_Product_NotFound_Category(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryService.getCategoryId(Long.valueOf("2"))).thenReturn(Optional.empty());
        List<String> list = new ArrayList<>();
        ProductDTO productDTO1 = new ProductDTO("1","2",1,list,"2",0);
        NotFoundCategory exception = Assertions.assertThrows(NotFoundCategory.class,
                () -> productServiceImpl.updateProduct(1L,productDTO1));
        assertThat(exception.getMessage(), is("Can not find product with id: 2"));
    }

    @Test
    public void test_top5_product_sort(){
        List<Product> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(mock(Product.class));
        }
        when(productRepository.SortProductByRatting()).thenReturn(list);
        List<Product> resule = productServiceImpl.top5ProductRatting();
        assertEquals(list.size(),resule.size());
    }
    @Test
    public void test_convert_DTO(){
        Product product1 = new Product(1L,"abc",10,null,4,category,null,"bcc",null,null,null);
        ProductDTO productDTO1 = productServiceImpl.convertProductDTO(product1);
        assertEquals(product1.getRatting(),productDTO1.getRating());
        assertEquals(product1.getTitle(),productDTO1.getName());

    }






}
