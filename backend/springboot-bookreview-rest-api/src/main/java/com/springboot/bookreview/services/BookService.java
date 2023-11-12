package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.bookDtos.BookDto;
import com.springboot.bookreview.entities.Book;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();
    BookDto getBookById(Long bookId);
}
