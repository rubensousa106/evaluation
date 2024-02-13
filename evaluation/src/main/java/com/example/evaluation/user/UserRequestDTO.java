package com.example.evaluation.user;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

public record UserRequestDTO(String username, String password, String email, Byte role,
                             @Getter LocalDate dateOfRegistration) {

}
