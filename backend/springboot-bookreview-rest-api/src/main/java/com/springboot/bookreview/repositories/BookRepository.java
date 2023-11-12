package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
