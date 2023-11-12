package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.authDtos.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @PostMapping(value = {"login", "signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        if(loginDto.getUsername().equals("admin") && loginDto.getPassword().equals("admin")) {
            return ResponseEntity.ok("User succesfully logged in!");
        }

        return new ResponseEntity<>("User not found in db", HttpStatus.BAD_REQUEST);

    }
}
