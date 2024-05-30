package org.dishdiary.service;

import org.dishdiary.domain.reviews.Review;
import org.dishdiary.domain.reviews.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public int save(Review review) {
        Review entitySavedInDB = reviewRepository.save(review);

        return entitySavedInDB.getId();
    }

    public List<Review> findReviewsByEstablishment(String establishment) {
        List<Review> allReviews = reviewRepository.findAll();

        if (allReviews.isEmpty()) {
            return new ArrayList<>();
        }

        return allReviews.stream()
                .filter(review -> establishment.equals(review.getEstabelecimento()))
                .toList();
    }
}
