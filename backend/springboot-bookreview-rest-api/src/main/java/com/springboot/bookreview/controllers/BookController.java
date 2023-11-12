package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.bookDtos.BookDto;
import com.springboot.bookreview.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //GET: api/books
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //GET: api/book/1
    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }
}
