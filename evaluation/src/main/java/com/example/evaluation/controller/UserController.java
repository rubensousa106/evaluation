package com.example.evaluation.controller;

import com.example.evaluation.authentication.AuthenticationRequest;
import com.example.evaluation.authentication.AuthenticationResponse;
import com.example.evaluation.authentication.AuthenticationService;
import com.example.evaluation.authentication.RegisterRequest;
import com.example.evaluation.domain.user.User;
import com.example.evaluation.domain.user.UserRequestDTO;
import com.example.evaluation.domain.user.UserResponseDTO;
import com.example.evaluation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationService authService;


    @GetMapping("all")
    public List<UserResponseDTO> getAll() {
        //List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
        System.out.println("Metodo getAll() de UserController executado.");
        return userList;
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> ResponseEntity.ok(new UserResponseDTO(user)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    /**
     * METODO TEMPORARIO, APENAS PARA TESTAR O REGISTO DE UTILIZADORES
     * SE FUNCIONAR, APAGAR O METODO SAVEUSER()
     * Endpoint para registar um utilizador
     * @param request
     * @return  AuthenticationResponse
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) { // RegisterRequest is a DTO, mudar dps
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     * METODO TEMPORARIO, APENAS PARA TESTAR
     * Endpoint para autenticar um utilizador
     * @param request
     * @return AuthenticationResponse
     */

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("create")
    public void saveUser(@RequestBody UserRequestDTO data) {
        User userData = new User(data);
        userRepository.save(userData);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("Usuário removido com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO data) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            // Crie um novo registro com base no existente e os dados fornecidos em UserRequestDTO
            User updatedUser = new User(existingUser.getId(), data.username(), data.password(), data.email(),  data.dateOfRegistration(),data.role());

            userRepository.save(updatedUser);

            return ResponseEntity.ok("Usuário atualizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o ID: " + id);
        }
    }
}
