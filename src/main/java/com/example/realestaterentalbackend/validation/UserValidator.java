package com.example.realestaterentalbackend.validation;

import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validName(String name) {
        return name.matches("^[A-Z][a-z]+");
    }

    public boolean validEmail(String email) {
        final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,7})$";
        return email.matches(EMAIL_PATTERN);
    }

    public boolean emailExists(String email) {
        // email not in db => findByEmail return null ; null != null => return false
        return userRepository.findByEmail(email) != null;
    }

    public boolean validPassword(String password) {
        final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return password.matches(PASSWORD_PATTERN);
    }

    public boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^07[0-9]{8}");
    }


    public boolean passwordsMatch(String password, String matchingPassword) {
        return password.equals(matchingPassword);
    }

    public List<String> validate(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if (emailExists(userDto.getEmail())) {
            errors.add("Email already exists in database!");
        }

        if (!validName(userDto.getFirstName())) {
            errors.add("Fist name is not valid!");
        }

        if (!validName(userDto.getLastName())) {
            errors.add("Last name is not valid!");
        }

        if (!validEmail(userDto.getEmail())) {
            errors.add("Email is not valid!");
        }

        if (!validPhoneNumber(userDto.getPhoneNumber())) {
            errors.add("Phone number is not valid!");
        }

        if (!validPassword(userDto.getPassword())) {
            errors.add("Password is not valid!");
        }

        if (!passwordsMatch(userDto.getPassword(), userDto.getMatchingPassword())) {
            errors.add("Passwords don't match!");
        }

        return errors;
    }
}
