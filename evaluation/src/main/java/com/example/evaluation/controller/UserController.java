package com.example.evaluation.controller;

import com.example.evaluation.user.User;
import com.example.evaluation.user.UserRepository;
import com.example.evaluation.user.UserRequestDTO;
import com.example.evaluation.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<UserResponseDTO> getAll() {

        //List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
        System.out.println("Metodo getAll() de UserController executado.");
        return userList;
    }

    @PostMapping
    public void saveUser(@RequestBody UserRequestDTO data) {
        User userData = new User(data);
        userRepository.save(userData);


    }
}
