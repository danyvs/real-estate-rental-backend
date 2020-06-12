package com.example.realestaterentalbackend.controller;

import com.example.realestaterentalbackend.dto.AdvertDto;
import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.exception.CustomException;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.UserRepository;
import com.example.realestaterentalbackend.request.AdvertRequest;
import com.example.realestaterentalbackend.request.UserRequest;
import com.example.realestaterentalbackend.service.AdvertService;
import com.example.realestaterentalbackend.service.MailService;
import com.example.realestaterentalbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserRequest userRequest;
    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public UserController(UserRepository userRepository, UserRequest userRequest, UserService userService,
                          MailService mailService) {
        this.userRepository = userRepository;
        this.userRequest = userRequest;
        this.userService = userService;
        this.mailService = mailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserDto userDto) {
        try {
            userRequest.isUserDataValid(userDto);
        } catch (CustomException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        User user = userService.registerNewUser(userDto);
        mailService.sendEmail(user);
        return ResponseEntity.ok("Register successful");
    }

//    @PostMapping("/login")
//    public boolean login(@RequestParam String email, @RequestParam String password) {
//        User user;
//        user = userRepository.findByEmail(email);
//
//        return user.getPassword().equals(password);
//    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, @RequestParam String oldPassword) {
        try {
            userRequest.isUserDataValid(userDto);
        } catch (CustomException exception) {
            if (!exception.getMessage().equals("Email already exists in database!"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        if (userService.updateUser(userDto, oldPassword)) {
            return ResponseEntity.ok("Update successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords don't match");
    }
}
