package com.example.evaluation.service;

import com.example.evaluation.domain.user.User;
import com.example.evaluation.domain.user.UserRole;
import com.example.evaluation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is ok")
    void loadUserByUsername() {
        //Given
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(repository.findByUsername(username)).thenReturn(Optional.of(user));
        //When
        UserDetails userDetails = authorizationService.loadUserByUsername(username);
        //Then
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        verify(repository, times(1)).findByUsername(username);
        //User user1 = new User(1L,"Mary","password1234", "email@test.com", LocalDate.now(),UserRole.USER );
        //User user2 = new User(2L,"Jonh","passwordTest", "test@test.com", LocalDate.now(),UserRole.USER );
//        verify(repository, times(1)).save(any()); //verifica se o metodo save foi chamado 1 vez

//        when(repository.findByUsername("Mary")).thenReturn(Optional.of(user1));
//        when(repository.findByUsername("Jonh")).thenReturn(Optional.of(user2));


    }
}
