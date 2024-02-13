package com.example.evaluation.user;

import java.time.LocalDate;
import java.util.Date;

public record UserResponseDTO(Long id, String username, String password, String email, Byte role,
                              LocalDate dateOfRegistration) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.getDateOfRegistration());
    }

}

