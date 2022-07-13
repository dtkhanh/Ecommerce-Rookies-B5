package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import com.example.ecommerce_rookies.modelDTO.CategoryDTO;
import com.example.ecommerce_rookies.models.Category;
import com.example.ecommerce_rookies.repository.CategoryRepository;
import com.example.ecommerce_rookies.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl( CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Category createCategory(Category category) {
        List<Category> categories = categoryRepository.findAll();
        for(Category category1 : categories){
            if(category1.getCategoryname().equals(category.getCategoryname()))
                throw  new NotFoundCategory.NotFoundsave(category.getCategoryname());
        }
        return categoryRepository.save(category);
    }
    @Override
    public Optional<Category> getCategoryId(Long id) {
        if(categoryRepository.findById(id).isEmpty())
            throw new NotFoundCategory(id);
        return categoryRepository.findById(id);
    }
    @Override
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }
    @Override
    public void deleteCategory(Long id) {
        if(categoryRepository.findById(id).isEmpty())
            throw new NotFoundCategory(id);
        categoryRepository.deleteById(id);
    }
    @Override
    public Category updateCategory(long id, Category category) {
        if(categoryRepository.findById(id).isEmpty())
            throw new NotFoundCategory(id);
        Category category1 = categoryRepository.getReferenceById(id);
        category1.setCategoryname(category.getCategoryname());
        return categoryRepository.save(category1);
    }
    @Override
    public Category getReferenceById(Long id) {
        if(categoryRepository.findById(id).isEmpty())
            throw new NotFoundCategory(id);
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public CategoryDTO convertToDto(Category cate){
        CategoryDTO categoryDTO = new CategoryDTO(cate.getId(), cate.getCategoryname());
        return categoryDTO;
    }
    @Override
    public List<CategoryDTO> convertToDtoList(List<Category> categoryList){
        List<CategoryDTO> list = new ArrayList<>();
        for (Category cate : categoryList) {
            list.add(convertToDto(cate));
        }
        return list;
    }

    @Override
    public Category convertToCategory(CategoryDTO categoryDTO) throws ParseException {
        Category cate = modelMapper.map(categoryDTO, Category.class);
        return cate;
    }

//    public CategoryDisplay convertToDisplay(CategoryDTO dto){
//        CategoryDisplay categoryDisplay = modelMapper.map(dto, CategoryDisplay.class);
//        return categoryDisplay;
//    }
//
//    public List<CategoryDisplay> convertToDisplayList(List<CategoryDTO> dtoList){
//        List<CategoryDisplay> displayList = new ArrayList<>();
//        for (CategoryDTO dto : dtoList) {
//            displayList.add(convertToDisplay(dto));
//        }
//        return displayList;
//    }




}
