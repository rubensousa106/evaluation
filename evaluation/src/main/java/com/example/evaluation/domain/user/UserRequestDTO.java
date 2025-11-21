package com.example.evaluation.domain.user;

import lombok.Getter;

import java.time.LocalDate;


public record UserRequestDTO(String username, String password, String email, UserRole role,
                             @Getter LocalDate dateOfRegistration) {

}
