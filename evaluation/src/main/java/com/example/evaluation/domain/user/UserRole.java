package com.example.evaluation.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    TEACHER("teacher"),
    NON_TEACHER("non-teacher");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}



