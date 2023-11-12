package com.springboot.bookreview.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;


    public Publisher() {}

    //Constructor for dto mapping

    public Publisher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Constructor for add dto mapping

    public Publisher(String name) {
        this.name = name;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
