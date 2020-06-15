package com.example.realestaterentalbackend.service;

import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.model.Role;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRoles(Collections.singletonList(Role.ROLE_USER));
        return userRepository.save(user);
    }

    public boolean updateUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        if (passwordEncoder.matches(userDto.getMatchingPassword(), user.getPassword())) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            if (!user.getPassword().equals(userDto.getPassword())) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            user.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
