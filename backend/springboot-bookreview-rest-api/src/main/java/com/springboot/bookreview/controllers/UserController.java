package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.authDtos.RegisterDto;
import com.springboot.bookreview.dto.userDtos.UserDto;
import com.springboot.bookreview.entities.User;
import com.springboot.bookreview.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST: api/users
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody RegisterDto user) {

        UserDto userAdded = userService.addUser(user);

        if(userAdded == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);

    }

}
