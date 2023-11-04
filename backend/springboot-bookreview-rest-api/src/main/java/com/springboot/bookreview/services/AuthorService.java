package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.authorDtos.AuthorAddDto;
import com.springboot.bookreview.dto.authorDtos.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();
    AuthorDto getAuthorById(Long authorId);
    AuthorDto addAuthor(AuthorAddDto authorAddDto);
    void deleteAuthor(Long authorId);
}
