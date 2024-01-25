package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.authDtos.LoginDto;
import com.springboot.bookreview.dto.authDtos.RegisterDto;
import com.springboot.bookreview.dto.userDtos.UserDto;
import com.springboot.bookreview.entities.User;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.UserRepository;
import com.springboot.bookreview.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public UserDto getUserByUsername(String username) {
        User userFromDb = userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User", "username", username));

        return mapEntityToDto(userFromDb);
    }

    @Override
    public UserDto authenticateUser(LoginDto login) {

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        User userFromDb = userRepository.findByUsername(login.getUsername()).orElseThrow(()
            -> new ResourceNotFoundException("User", "username", login.getUsername()));

        boolean isMatch = bcrypt.matches(login.getPassword(), userFromDb.getPassword());

        if(!isMatch) {
            return null;
        }

        return mapEntityToDto(userFromDb);


    }


    @Override
    public UserDto addUser(RegisterDto userToAdd) {

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bcrypt.encode(userToAdd.getPassword());
        userToAdd.setPassword(encryptedPassword);

        User savedUser = userRepository.save(mapRegisterToUser(userToAdd));

        return mapEntityToDto(savedUser);

    }


    public static UserDto mapEntityToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());

        return userDto;
    }

    public static User mapRegisterToUser(RegisterDto user) {

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());

        return newUser;
    }


}
