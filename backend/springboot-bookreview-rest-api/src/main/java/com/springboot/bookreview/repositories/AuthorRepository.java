package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
