package com.example.ecommerce_rookies.TestService;


import java.util.*;

import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.ecommerce_rookies.modelDTO.CategoryDTO;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.services.impl.CategoryServiceImpl;

import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

public class CategoryServicesTest {

    private CategoryServiceImpl categoryServiceImpl;

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;
    private CategoryDTO categoryDTO;
    private List<CategoryDTO> responseDtoList ;
    private Category category;
    private Category category1;
    private   List<Category> categoryList ;
    private ModelMapper modelMapper;


    @BeforeEach
    void test(){
        this.categoryRepository = mock(CategoryRepository.class);
        this.mapper = mock(ModelMapper.class);
        this.categoryServiceImpl = new CategoryServiceImpl(categoryRepository, mapper);
        categoryDTO = mock(CategoryDTO.class);
        responseDtoList = new ArrayList<>();
        category= category1 = mock(Category.class);
        categoryList = new ArrayList<>();
        modelMapper = mock(ModelMapper.class);

    }

    @Test
    void getAllCategories() {
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
    void find_All_Categorys_returnListCategory(){
        for(int i=0;i<3; i++){
            Category category = mock(Category.class);
            categoryList.add(category);
        }
        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(categoryServiceImpl.getCategory()).thenReturn(categoryList);
        assertEquals(categoryList.size() , categoryServiceImpl.getCategory().size());
    }
    @Test
    void delete_CategoryPBuId(){
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        categoryServiceImpl.deleteCategory(id);
        Mockito.verify(categoryRepository).deleteById(id);
    }
    @Test
    void testUpdateCategory_NotFound(){
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        Category category2 = mock(Category.class);
        Category category3 = mock(Category.class);

        when(categoryRepository.getReferenceById(id)).thenReturn((category));
        category.setCategoryname(category2.getCategoryname());
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category2.getCategoryname(), category.getCategoryname());


        when(categoryRepository.getReferenceById(id)).thenReturn((category1));
        category1.setCategoryname(category2.getCategoryname());
        when(categoryRepository.save(category1)).thenReturn(category3);
        when(categoryServiceImpl.updateCategory(id,category2)).thenReturn(category3);
        assertEquals(categoryServiceImpl.updateCategory(id,category2), category3);
    }
    @Test
    void testFindCategory_NotFoundById(){
        Long id = new Random().nextLong();
        when(categoryRepository.findById(ArgumentMatchers.any())).thenThrow(new NotFoundCategory(ArgumentMatchers.any()));
        assertThrows(NotFoundCategory.class, () -> categoryServiceImpl.updateCategory(id,category));
    }
    @Test
    public void create_Category_successfully(){
        Long id = 1L;
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryServiceImpl.createCategory(category)).thenReturn(category);
        assertEquals(categoryServiceImpl.createCategory(category), category);
        verify(categoryRepository, times(1)).save(category);
    }
    @Test
    public void test_convert_DTO(){
        assertEquals(categoryServiceImpl.convertToDto(category).getName(),category.getCategoryname());
        assertEquals(categoryServiceImpl.convertToDto(category).getId(),category.getId());
    }


}
