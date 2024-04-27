package com.taskify.taskify.controller;

import com.taskify.taskify.exception.UserNotFountException;
import com.taskify.taskify.model.User;
import com.taskify.taskify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")

public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/user")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFountException(id));
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
            .map(user -> {
                user.setName(newUser.getName());
                user.setUsername(newUser.getUsername());
                user.setEmail(newUser.getEmail());
                return userRepository.save(user);
            }).orElseThrow(()-> new UserNotFountException(id));
    }
}
