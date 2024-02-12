package com.example.evaluation.user;

import java.util.Date;

public record UserResponseDTO(Long id, String username, String password, String email, Byte role,
                              Date dateOfRegistration) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.getDateOfRegistration());
    }

}

