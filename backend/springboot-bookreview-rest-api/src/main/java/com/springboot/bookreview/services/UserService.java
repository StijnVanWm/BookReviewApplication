package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.authDtos.LoginDto;
import com.springboot.bookreview.dto.authDtos.RegisterDto;
import com.springboot.bookreview.dto.userDtos.UserDto;

public interface UserService {

    UserDto getUserById(Long userId);
    UserDto authenticateUser(LoginDto login);
    UserDto addUser(RegisterDto userToAdd);
    UserDto getUserByUsername(String username);
}
