package com.example.realestaterentalbackend.controller;

import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.exception.CustomException;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.UserRepository;
import com.example.realestaterentalbackend.request.UserRequest;
import com.example.realestaterentalbackend.service.MailService;
import com.example.realestaterentalbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserRequest userRequest;
    private final UserService userService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, UserRequest userRequest, UserService userService,
                          MailService mailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRequest = userRequest;
        this.userService = userService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, Friend";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/failure")
    public String failure() {
        return "failure";
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> user;
        user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return ResponseEntity.ok("Login successful");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect password");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }

    @GetMapping("/updateUser")
    public UserDto showUserToUpdate() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("You are not logged in"));
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setMatchingPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {
        try {
            userRequest.isUserDataValid(userDto);
        } catch (CustomException exception) {

            if (!exception.getMessage().substring(0, 33).equals("Email already exists in database!"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        if (userService.updateUser(userDto)) {
            return ResponseEntity.ok("Update successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords don't match");
    }
}
