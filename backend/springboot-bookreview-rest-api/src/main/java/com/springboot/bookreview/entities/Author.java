package com.springboot.bookreview.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author() {}

    //Constructor for dto mapping

    public Author(Long id, String name, String firstName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
    }

    //Constructor for add dto mapping

    public Author(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
