package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.authorDtos.AuthorAddDto;
import com.springboot.bookreview.dto.authorDtos.AuthorDto;
import com.springboot.bookreview.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //GET: api/authors
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    //GET: api/authors/1
    @GetMapping("{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable(name = "id") Long authorId) {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    //POST: api/authors
    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorAddDto authorAddDto) {

        AuthorDto authorFromDb = authorService.addAuthor(authorAddDto);
        if(authorFromDb == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(authorFromDb, HttpStatus.CREATED);
    }

    //DELETE: api/authors/2
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable(name = "id") Long authorId) {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
