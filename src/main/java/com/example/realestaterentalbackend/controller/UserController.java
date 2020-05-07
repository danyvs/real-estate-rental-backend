package com.example.realestaterentalbackend.controller;

import com.example.realestaterentalbackend.dto.AdvertDto;
import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.exception.CustomException;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.UserRepository;
import com.example.realestaterentalbackend.request.UserRequest;
import com.example.realestaterentalbackend.service.AdvertService;
import com.example.realestaterentalbackend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserRequest userRequest;
    private final MailService mailService;
    private final AdvertService advertService;

    @Autowired
    public UserController(UserRepository userRepository, UserRequest userRequest, MailService mailService,
                          AdvertService advertService) {
        this.userRepository = userRepository;
        this.userRequest = userRequest;
        this.mailService = mailService;
        this.advertService = advertService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserDto userDto) {
        User user;
        try {
            user = userRequest.validateData(userDto);
        } catch (CustomException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        mailService.sendEmail(user);
        return ResponseEntity.ok("Register successful");
    }

    @PostMapping("/addAdvert")
    public void addNewAdvert(@Valid @RequestBody AdvertDto advertDto) {
        // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail("root@info.ro");
        advertService.addNewAdvert(advertDto, user);
    }
}
