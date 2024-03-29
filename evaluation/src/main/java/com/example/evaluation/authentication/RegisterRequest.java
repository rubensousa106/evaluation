package com.example.evaluation.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private  String username;
    private  String password;
    private  String email;
    private LocalDate dateOfRegistration;


    public LocalDate getDateofRegistration() {
        return this.dateOfRegistration;
    }
}
