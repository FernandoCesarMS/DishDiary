package org.dishdiary.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {
    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getReviews(){
        return ResponseEntity.ok("Valor retornado com sucesso");
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postReview(){
        return ResponseEntity.ok("Valor criado!");
    }
}
