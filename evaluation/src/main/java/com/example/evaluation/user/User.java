package com.example.evaluation.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

