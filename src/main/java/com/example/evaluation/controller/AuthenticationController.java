package com.example.evaluation.controller;

import com.example.evaluation.domain.user.AuthenticationDTO;
import com.example.evaluation.domain.user.LoginResponseDTO;
import com.example.evaluation.domain.user.RegisterDTO;
import com.example.evaluation.domain.user.User;
import com.example.evaluation.infra.security.TokenService;
import com.example.evaluation.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("auth")
@Validated  // Adicione a anotação Validated na classe para ativar a validação
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        // Autenticar username
        var auth = authenticationManager.authenticate(usernamePassword);

        //o Principal é o utilizador que está autenticado
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.userRepository.findByUsername(data.username()) != null){
            return ResponseEntity.badRequest().build();
        }else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.username(), encryptedPassword, data.role());
            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        }
    }
}
