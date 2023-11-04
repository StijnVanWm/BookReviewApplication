package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.authorDtos.AuthorAddDto;
import com.springboot.bookreview.dto.authorDtos.AuthorDto;
import com.springboot.bookreview.entities.Author;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.AuthorRepository;
import com.springboot.bookreview.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(author
                -> mapEntityToDto(author)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(Long authorId) {

        Author authorFromDb = authorRepository.findById(authorId).orElseThrow(()
                -> new ResourceNotFoundException("Author", "id", authorId));

        return mapEntityToDto(authorFromDb);
    }

    @Override
    public AuthorDto addAuthor(AuthorAddDto authorAddDto) {

        Author authorToAdd = mapAddDtoToEntity(authorAddDto);
        Author authorFromDb = authorRepository.save(authorToAdd);
        return mapEntityToDto(authorFromDb);
    }

    @Override
    public void deleteAuthor(Long authorId) {

        Author authorFromDb = authorRepository.findById(authorId).orElseThrow(()
                -> new ResourceNotFoundException("Author", "id", authorId));

        authorRepository.delete(authorFromDb);
    }

    private AuthorDto mapEntityToDto(Author author) {

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setFirstName(author.getFirstName());

        return authorDto;
    }


    private Author mapAddDtoToEntity(AuthorAddDto authorAddDto) {
        return new Author(
                authorAddDto.getName(),
                authorAddDto.getFirstName()
        );
    }
}
