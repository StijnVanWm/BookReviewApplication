package com.springboot.bookreview.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "category")
    private Set<Book> books;

    public Category() {}

    //Constructor for dto mapping

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Constructor for add dto mapping

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
