package com.example.realestaterentalbackend.controller;

import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/modifyUser/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found " + id));
    }

    @PostMapping("/modifyUser/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable int id, @RequestBody User user) {
        User databaseUser = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found " + id));
        if (databaseUser.getId() == user.getId() && databaseUser.getEmail().equals(user.getEmail()) &&
                databaseUser.getPassword().equals(user.getPassword())) {
            userRepository.save(user);
            return ResponseEntity.ok("Successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id, email and password can't be modified");
    }
}
