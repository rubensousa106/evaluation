package com.example.evaluation.domain.user;

import java.time.LocalDate;

public record UserResponseDTO(Long id, String username, String password, String email, UserRole role,
                              LocalDate dateOfRegistration) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.getDateOfRegistration());
    }

}

