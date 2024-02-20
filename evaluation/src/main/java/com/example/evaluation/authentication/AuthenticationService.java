package com.example.evaluation.authentication;


import com.example.evaluation.infra.security.JwtService;
import com.example.evaluation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    //private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = com.example.evaluation.domain.user.User.builder() //ele estava a ir buscar o user do pacote errado
                .username(request.getUsername())
                //.password(passwordEncoder.encode(request.getPassword()))
                .password(passwordEncoder(request.getPassword()))
                .email(request.getEmail())
                .role(com.example.evaluation.domain.user.UserRole.USER)
                .dateOfRegistration(LocalDate.now())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ) // se nao conseguir autenticar, lanca excecao
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    //password encoder pelo BCryptPasswordEncoder
    public String passwordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


}
