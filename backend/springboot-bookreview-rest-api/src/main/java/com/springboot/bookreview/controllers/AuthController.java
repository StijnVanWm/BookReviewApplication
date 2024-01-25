package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.authDtos.AuthDto;
import com.springboot.bookreview.dto.authDtos.LoginDto;
import com.springboot.bookreview.dto.authDtos.VerifyDto;
import com.springboot.bookreview.dto.userDtos.UserDto;
import com.springboot.bookreview.security.JwtTokenProvider;
import com.springboot.bookreview.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;
    private JwtTokenProvider tokenProvider;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(value = {"login"})
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto) {

        if(loginDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        UserDto userFromDb = userService.authenticateUser(loginDto);

        if(userFromDb == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        String token = tokenProvider.generateToken(loginDto);
        AuthDto authDto = new AuthDto();
        authDto.setAccessToken(token);

        return ResponseEntity.ok(authDto);

    }

    @GetMapping("/verify")
    public ResponseEntity<VerifyDto> verify(@RequestHeader(name = "Authorization") String token) {

        VerifyDto verifyDto = new VerifyDto();
        try {

            boolean isValidToken = tokenProvider.validToken(token);
            UserDto tokenHolder = userService.getUserByUsername(tokenProvider.getUsername(token));

            verifyDto.setVerified(isValidToken);
            verifyDto.setUserId(tokenHolder.getId());
            return ResponseEntity.ok(verifyDto);

        } catch (Exception e) {
            verifyDto.setVerified(false);
            return ResponseEntity.ok(verifyDto);
        }

    }
}
