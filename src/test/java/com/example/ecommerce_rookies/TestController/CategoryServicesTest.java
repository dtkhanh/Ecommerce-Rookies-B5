package com.example.ecommerce_rookies.TestController;


import java.text.ParseException;
import java.util.*;

import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.ecommerce_rookies.modelDTO.CategoryDTO;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.models.Product;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import com.example.ecommerce_rookies.services.impl.CategoryServiceImpl;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.*;

public class CategoryServicesTest {

    private CategoryServiceImpl categoryServiceImpl;

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    @BeforeEach
    void test(){
        this.categoryRepository = mock(CategoryRepository.class);
        this.mapper = mock(ModelMapper.class);
        this.categoryServiceImpl = new CategoryServiceImpl(categoryRepository, mapper);
    }

     @Test
    void delete() {
    }

    @Test
    void getAllCategories() {
        List<CategoryDTO> responseDtoList = new ArrayList<>();
        when(categoryServiceImpl.convertToDtoList(categoryServiceImpl.getCategory())).thenReturn(responseDtoList);
        List<Category> foundCategory = categoryServiceImpl.getCategory();
        assertEquals(responseDtoList.size(), foundCategory.size());
    }
    @Test
    void findCategoryId_NotFound(){
        Long id = new Random().nextLong();
        when(categoryRepository.findById(ArgumentMatchers.any())).thenThrow(new NotFoundCategory(ArgumentMatchers.any()));
        assertThrows(NotFoundCategory.class, () -> categoryServiceImpl.getCategoryId(id));
    }
    @Test
    void findCategoryId(){
        Long id = new Random().nextLong();
        Category category = mock(Category.class);
        when(categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(category));
        assertEquals(categoryServiceImpl.getCategoryId(id), Optional.of(category));
    }
    @Test
    void findCategorys(){
        List<Category> categoryList = new ArrayList<>();
        for(int i=0;i<3; i++){
            Category category = mock(Category.class);
            categoryList.add(category);
        }
        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(categoryServiceImpl.getCategory()).thenReturn(categoryList);
        assertEquals(categoryList.size() , categoryServiceImpl.getCategory().size());
    }
    @Test
    void testUpdateCategory_NotFound(){
        Long id = new Random().nextLong();
        Category category = mock(Category.class);
        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        when(categoryRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(category));
        when(categoryRepository.getReferenceById(id)).thenReturn((category1));
        when(categoryServiceImpl.updateCategory(id,category1)).thenReturn(category1);
        assertEquals(categoryServiceImpl.updateCategory(id,category1), category1);
    }
    @Test
    void testUpdateCategory_NotFoundById(){
        Long id = new Random().nextLong();
        Category category = mock(Category.class);
        when(categoryRepository.findById(ArgumentMatchers.any())).thenThrow(new NotFoundCategory(ArgumentMatchers.any()));
        assertThrows(NotFoundCategory.class, () -> categoryServiceImpl.updateCategory(id,category));
    }




}
