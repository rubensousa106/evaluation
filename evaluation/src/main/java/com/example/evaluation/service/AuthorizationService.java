package com.example.evaluation.service;

import com.example.evaluation.infra.security.JwtAuthenticationFilter;
import com.example.evaluation.infra.security.JwtService;
import com.example.evaluation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService extends JwtAuthenticationFilter implements UserDetailsService {
    @Autowired
    UserRepository repository;

    public AuthorizationService(UserDetailsService userDetailsService, JwtService jwtService) {
        super(userDetailsService, jwtService);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
