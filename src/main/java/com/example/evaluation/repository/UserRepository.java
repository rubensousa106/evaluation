package com.example.evaluation.repository;

import com.example.evaluation.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    //metodo para consultar o utilizador pelo username
    UserDetails findByUsername(String username);
}

