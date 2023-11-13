package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.userDtos.UserDto;
import com.springboot.bookreview.entities.User;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.UserRepository;
import com.springboot.bookreview.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDto getUserById(Long userId) {
        User userFromDb = userRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", userId));

        return mapEntityToDto(userFromDb);
    }

    public static UserDto mapEntityToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());

        return userDto;
    }

}
