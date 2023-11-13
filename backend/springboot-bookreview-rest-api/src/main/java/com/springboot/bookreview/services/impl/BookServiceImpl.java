package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.bookDtos.BookDto;
import com.springboot.bookreview.entities.Book;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.BookRepository;
import com.springboot.bookreview.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(book -> mapEntityToDto(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book bookFromDb = bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "id", bookId));

        return mapEntityToDto(bookFromDb);
    }


    //MAPPING
    public static BookDto mapEntityToDto(Book book) {

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setBookCover(book.getBookCover());
        bookDto.setPageCount(book.getPageCount());
        bookDto.setAuthor(AuthorServiceImpl.mapEntityToDto(book.getAuthor()));
        bookDto.setCategory(CategoryServiceImpl.mapEntityToDto(book.getCategory()));
        bookDto.setPublisher(PublisherServiceImpl.mapEntityToDto(book.getPublisher()));

        return bookDto;
    }
}
