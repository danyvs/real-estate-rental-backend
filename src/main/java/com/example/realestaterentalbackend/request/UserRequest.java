package com.example.realestaterentalbackend.request;

import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.exception.CustomException;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.service.UserService;
import com.example.realestaterentalbackend.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRequest {
    final UserService userService;
    final UserValidator userValidator;

    @Autowired
    public UserRequest(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    public User validateData(UserDto userDto) throws CustomException {
        List<String> errors = userValidator.validate(userDto);
        if (!errors.isEmpty()) {
            String errorMsg = String.join(", ", errors);
            throw new CustomException(errorMsg);
        } else {
            return userService.registerNewUser(userDto);
        }
    }
}
