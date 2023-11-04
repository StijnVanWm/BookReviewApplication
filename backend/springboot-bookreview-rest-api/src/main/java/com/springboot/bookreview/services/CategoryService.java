package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.catergoryDtos.CategoryAddDto;
import com.springboot.bookreview.dto.catergoryDtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto addCategory(CategoryAddDto categoryAddDto);
    CategoryDto getCategoryById(Long categoryId);
    void deleteCategory(Long categoryId);
}
