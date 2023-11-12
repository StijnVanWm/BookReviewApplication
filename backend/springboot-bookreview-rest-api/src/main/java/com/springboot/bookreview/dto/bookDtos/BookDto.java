package com.springboot.bookreview.dto.bookDtos;

import com.springboot.bookreview.dto.authorDtos.AuthorDto;
import com.springboot.bookreview.dto.catergoryDtos.CategoryDto;
import com.springboot.bookreview.dto.publisherDtos.PublisherDto;


public class BookDto {
    private Long id;
    private String title;
    private int pageCount;
    private String bookCover;
    private AuthorDto author;
    private PublisherDto publisher;
    private CategoryDto category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public PublisherDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDto publisher) {
        this.publisher = publisher;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
