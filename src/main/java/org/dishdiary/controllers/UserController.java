package org.dishdiary.controllers;

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

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUser(@RequestBody User obj){
        int id = userService.save(obj);
        URI uri = URI.create("jdbc:postgresql://localhost:5432/dishdiary/users/" + id);
        return ResponseEntity.created(uri).body("Usuário criado com sucesso");
    }
}
