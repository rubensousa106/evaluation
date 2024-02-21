package com.example.evaluation.infra.security;

import com.example.evaluation.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter  {
    /**
     * Classe que implementa um filtro de segurança, e acontece apenas uma vez por cada pedido/requisição
     */


    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request); //recuperar o token
        if (token != null) {
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByUsername(login); //encontrei o meu utilizador

            //fazer as verificações necessárias ao utilizador
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication); //adicionar o utilizador ao contexto de segurança
        }
        filterChain.doFilter(request, response); //passar para o proximo filtro

    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}

