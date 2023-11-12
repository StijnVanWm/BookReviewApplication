package com.springboot.bookreview.entities;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    public Role() {}

    //Constructor for dto mapping
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Constructor for addDtoMapping
    public Role(String name) {
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setName(String name) {
        this.name = name;
    }
}
