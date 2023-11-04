package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.catergoryDtos.CategoryAddDto;
import com.springboot.bookreview.dto.catergoryDtos.CategoryDto;
import com.springboot.bookreview.entities.Category;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.CategoryRepository;
import com.springboot.bookreview.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categoriesFromDb = categoryRepository.findAll();

        return categoriesFromDb.stream().map(category -> mapEntityToDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto addCategory(CategoryAddDto categoryAddDto) {

        Category categoryToAdd = mapAddDtoToEntity(categoryAddDto);

        Category categoryFromDb = categoryRepository.save(categoryToAdd);

        return mapEntityToDto(categoryFromDb);

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        Category categoryFromDb = categoryRepository.findById(categoryId).orElseThrow(()
                -> new ResourceNotFoundException("Category", "id", categoryId));

        return mapEntityToDto(categoryFromDb);
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category categoryFromDb = categoryRepository.findById(categoryId).orElseThrow(()
                -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(categoryFromDb);

    }


    private CategoryDto mapEntityToDto(Category category) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;

    }

    private Category mapAddDtoToEntity(CategoryAddDto categoryAddDto) {
        return new Category(categoryAddDto.getName());
    }
}
