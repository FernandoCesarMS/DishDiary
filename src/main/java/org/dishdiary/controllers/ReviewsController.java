package org.dishdiary.controllers;

import org.dishdiary.domain.reviews.Review;
import org.dishdiary.domain.reviews.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    @Autowired
    private ReviewRepository repository;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReviews(){
        List<Review> allReviews = repository.findAll();
        return ResponseEntity.ok(allReviews);
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postReview(){
        return ResponseEntity.ok("Valor criado!");
    }
}
