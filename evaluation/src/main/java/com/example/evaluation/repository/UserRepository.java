package com.example.evaluation.repository;

import com.example.evaluation.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //metodo para consultar o utilizador pelo username

    //UserDetails findByUsername(String username); //metodo para consultar o utilizador pelo Username, e retorna um UserDetails
    //Optional<User> findByEmail(String email); //metodo para consultar o utilizador pelo Email, e retorna um User

    Optional<User> findByUsername(String username);
}



