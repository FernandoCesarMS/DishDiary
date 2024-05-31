package org.dishdiary.controllers;

import org.dishdiary.domain.requests.ValidateUserRequest;
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

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUser(@RequestBody User obj){
        int id = userService.save(obj);
        URI uri = URI.create("jdbc:postgresql://localhost:5432/dishdiary/users/" + id);
        return ResponseEntity.created(uri).body("Usuário criado com sucesso");
    }

    @GetMapping(value = "/validate", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> validateUserExist(@RequestBody ValidateUserRequest request){
        boolean userExists = userService.validateIfUserExists(request);

        if (userExists) {
            return ResponseEntity.ok().body(DefaultResponse.builder().message("Usuário existe no banco de dados").build());
        }
        return ResponseEntity.unprocessableEntity().body(DefaultResponse.builder().message("Usuário não existe no banco de dados").build());
    }
}
