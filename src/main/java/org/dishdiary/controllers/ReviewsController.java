package org.dishdiary.controllers;

import org.dishdiary.domain.responses.DefaultResponse;
import org.dishdiary.domain.responses.FindAllReviewsResponse;
import org.dishdiary.domain.reviews.Review;
import org.dishdiary.enums.FoodTypeEnum;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FindAllReviewsResponse>> getReviews(
            @RequestHeader(value = "food-type", required = false) FoodTypeEnum foodType,
            @RequestHeader(value = "establishment", required = false) String establishment,
            @RequestHeader(value = "customer-name", required = false) String customerName,
            @RequestHeader(value = "customer-cpf") String customerCpf) {
        if (foodType != null) {
            return ResponseEntity.ok(reviewService.findByFoodType(foodType, customerCpf));
        }

        if (establishment != null) {
            return ResponseEntity.ok(reviewService.findReviewsByEstablishment(establishment, customerCpf));
        }

        if (customerName != null) {
            return ResponseEntity.ok(reviewService.findReviewsByCustomerName(customerName, customerCpf));
        }

        return ResponseEntity.ok(reviewService.findAll(customerCpf));
    }

    @GetMapping(value = "/customer/{cpf}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> getReviewsByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(reviewService.findReviewsByCpf(cpf));
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> postReview(@RequestBody Review obj){
        int id = reviewService.save(obj);

        URI uri = URI.create("/dishdiary/reviews/" + id);
        DefaultResponse response = DefaultResponse.builder()
                .message("Review criada com sucesso")
                .data(obj)
                .build();

        return ResponseEntity.created(uri).body(response);
    }
}
