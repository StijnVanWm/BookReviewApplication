package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.catergoryDtos.CategoryAddDto;
import com.springboot.bookreview.dto.catergoryDtos.CategoryDto;
import com.springboot.bookreview.entities.Category;
import com.springboot.bookreview.services.CategoryService;
import org.hibernate.query.results.ResultBuilderEntityValued;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //GET: api/categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //GET: api/categories/1
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    //POST: api/categories
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryAddDto categoryAddDto) {

        CategoryDto categoryAddedToDb = categoryService.addCategory(categoryAddDto);

        if(categoryAddedToDb == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoryAddedToDb, HttpStatus.CREATED);

    }


    //DELETE: api/categories/5
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
