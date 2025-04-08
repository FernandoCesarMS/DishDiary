package org.dishdiary.controllers;

import org.dishdiary.domain.responses.DefaultResponse;
import org.dishdiary.domain.users.User;
import org.dishdiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/{cpf}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserByCpf(@PathVariable String cpf){
        User user = userService.findByCpf(cpf);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/login", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> validateLogin(
            @RequestHeader(value = "cpf", required = false) String cpf,
            @RequestHeader(value = "password", required = false) String password) {
        if (!userService.isLoginCorrect(cpf, password)) {
            DefaultResponse response = DefaultResponse.builder()
                    .message("Senha inválida")
                    .build();

            return ResponseEntity.unprocessableEntity().body(response);
        }

        DefaultResponse response = DefaultResponse.builder()
                .message("Usuário válido")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> postUser(@RequestBody User obj){
        int id = userService.save(obj);
        URI uri = URI.create("/dishdiary/users/" + id);
        DefaultResponse response = DefaultResponse.builder()
                .message("Usuário criado com sucesso")
                .data(obj)
                .build();

        return ResponseEntity.created(uri).body(response);
    }
}
