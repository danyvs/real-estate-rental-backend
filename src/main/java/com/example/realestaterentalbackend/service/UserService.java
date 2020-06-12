package com.example.realestaterentalbackend.service;

import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return userRepository.save(user);
    }

    public boolean updateUser(UserDto userDto, String oldPassword) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user.getPassword().equals(oldPassword)) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());
            user.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
