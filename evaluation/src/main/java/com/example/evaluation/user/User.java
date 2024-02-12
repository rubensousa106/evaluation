package com.example.evaluation.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private byte role;
    private Date dateOfRegistration;

    public User(UserRequestDTO data) {
        this.username = data.username();
        this.password = data.password();
        this.email = data.email();
        this.role = data.role();
        this.dateOfRegistration = data.dateOfRegistration();
    }

}

