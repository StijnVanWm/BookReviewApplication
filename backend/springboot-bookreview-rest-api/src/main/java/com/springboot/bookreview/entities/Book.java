package com.springboot.bookreview.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "pageCount", nullable = false)
    private int pageCount;

    @Column(name = "bookCover")
    private String bookCover;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;

    public Book() {
    }

    public Book(Long id, String title, int pageCount, String bookCover, boolean isDeleted, Author author, Publisher publisher, Category category) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.bookCover = bookCover;
        this.isDeleted = isDeleted;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
    }

    public Book(Long id, String title, int pageCount, String bookCover, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.bookCover = bookCover;
        this.isDeleted = isDeleted;
    }

    public Book(String title, int pageCount, String bookCover, boolean isDeleted) {
        this.title = title;
        this.pageCount = pageCount;
        this.bookCover = bookCover;
        this.isDeleted = isDeleted;
    }

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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
