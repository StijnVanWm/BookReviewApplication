package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //implements all crud operations needed
}
