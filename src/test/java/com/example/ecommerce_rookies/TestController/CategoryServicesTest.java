package com.example.ecommerce_rookies.TestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServicesTest {
    @MockBean
    CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void TestcreateCategory() {
        Product product = new Product();
        Category category = new Category();
        category.setId(10L);
        category.setCategoryname("Áo quần");
        product.setId(10L);
        product.setTitle("Áo nam");
        product.setCategory(category);
        product.setPrice(49.99f);
        product.setRatting(10);

        Optional<Category> cate = categoryRepository.findById(10L);
        if (!cate.isEmpty()) {
            Set<Product> products = cate.get().getProducts();
            Product testproduct = new Product();
            for(Product product1 : products){
                testproduct=product1;
            }
            assertEquals(product.getTitle(),testproduct.getTitle());
            assertEquals(product.getPrice(),testproduct.getPrice());
            assertEquals(product.getRatting(),testproduct.getRatting());

        }
    }
    @Test
    void whenGetAll_shouldReturnList() {
        List<Category> categories = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            categories.add(new Category((long)i,"Category",null));
        }
        when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> categorylist = categoryService.getCategory();
        assertThat(categorylist.size()).isEqualTo(categories.size());

    }



}
