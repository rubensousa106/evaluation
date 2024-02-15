package com.example.evaluation.user;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String username;
    private String password;
    private String email;
    private byte role;
    private LocalDate dateOfRegistration;

    public User(UserRequestDTO data) {
        this.username = data.username();
        this.password = data.password();
        this.email = data.email();
        this.role = data.role();
        this.dateOfRegistration = data.dateOfRegistration();
    }

}

