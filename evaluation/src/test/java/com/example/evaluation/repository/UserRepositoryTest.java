package com.example.evaluation.repository;

import com.example.evaluation.domain.user.UserRequestDTO;
import com.example.evaluation.domain.user.User;
import com.example.evaluation.domain.user.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;
    @Test
    @DisplayName("Should get User successfully from DataBase")
    void findByUsernameSuccess() {
        String username = "jonh";
        UserRequestDTO data = new UserRequestDTO(username, "password", "email", UserRole.USER, LocalDate.now());
        this.createUser(data);

        Optional<User> foundedUser = this.userRepository.findByUsername(data.username());
        assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DataBase when User not exists")
    void findByUsernameInsuccess() {
        String username = "joao";


        Optional<User> foundedUser = this.userRepository.findByUsername(username);
        assertThat(foundedUser.isEmpty()).isTrue();
    }

    private User createUser(UserRequestDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}
