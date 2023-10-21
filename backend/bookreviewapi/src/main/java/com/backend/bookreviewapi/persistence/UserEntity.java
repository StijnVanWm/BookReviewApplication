package com.backend.bookreviewapi.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "dbo.Users")
public class UserEntity {

    @Id
    private Long id;
    private Long roleId;
    //private RoleEntity role;
    private String userName;
    private String password;
    private String name;
    private String firstName;
    private String email;
    private String phoneNumber;
    private Date creationDate;

    public UserEntity() {
        //Empty constructor
    }


}
