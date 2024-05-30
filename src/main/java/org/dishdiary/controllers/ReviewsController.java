package org.dishdiary.controllers;

import org.dishdiary.domain.reviews.Review;
import org.dishdiary.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> getReviews(){
        return ResponseEntity.ok(reviewService.findAll());
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postReview(@RequestBody Review obj){
        int id = reviewService.save(obj);
        URI uri = URI.create("jdbc:postgresql://localhost:5432/dishdiary/reviews/" + id);
        return ResponseEntity.created(uri).body("Review criada com sucesso");
    }
}
