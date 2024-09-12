package com.example.evaluation.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {

    //random key hexadecimal of 256 bits
    private static final String SECRET_KEY = "c5a2f9d847e831b4104d32a8e3c7b6af7113e70a44e858369f7fe710826c9423";
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);/*retorna o subject do token. CLASS::nomedometodo()*/
    }


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        Optional<String> role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(authority -> authority.getAuthority());

        role.ifPresent(value -> claims.put("role", value));
        return generateToken(claims, userDetails);
    }

    /**
     * metodo para gerar um token
     *
     * @param extraClaims
     * @param userDetails
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7)) /*token expira em 7 dias*/
                .signWith(getSigningKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //retorna true se o token for valido
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); //retorna true se o token estiver expirado
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);//retorna a data de expiração do token
    }

    /**
     * metodo para extrair dividir o token em partes e extrair as reivindicações
     *
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * metodo para obter a chave de assinatura
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String refreshToken(String refreshToken) {
        return Jwts
                .builder()
                .setSubject(refreshToken)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSigningKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }
}
