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
    final UserValidator userValidator;

    @Autowired
    public UserRequest(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public void isUserDataValid(UserDto userDto) throws CustomException {
        List<String> errors = userValidator.validate(userDto);
        if (!errors.isEmpty()) {
            String errorMsg = String.join(", ", errors);
            throw new CustomException(errorMsg);
        }
    }
}
