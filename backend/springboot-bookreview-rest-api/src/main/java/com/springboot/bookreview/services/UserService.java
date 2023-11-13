package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.userDtos.UserDto;

public interface UserService {

    UserDto getUserById(Long userId);
}
