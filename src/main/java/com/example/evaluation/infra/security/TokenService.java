package com.example.evaluation.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.evaluation.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service

public class TokenService {
    @Value("${api.security.token.secret")
    private String secret;

    /**
     * Gera um token para o utilizador, com uma camada extra de proteccao
     * @param user
     * @return String
     */
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); //chave para adicionar uma camada de protecção extra ao token
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating token", exception);
        }
    }

    /**
     * Valida o token do utilizador
     * @param token
     * @return String o Username que guardamos no token
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception exception) {
            return ""; // O metodo que vai retornar a msg de erro, "expirado,invalido,nao-autorizado"
        }
    }


    /**
     * Gera a data de expiração do token
     * @return Instant
     */
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }
}
