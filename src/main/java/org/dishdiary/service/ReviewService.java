package org.dishdiary.service;

import org.dishdiary.domain.responses.FindAllReviewsResponse;
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

    public List<FindAllReviewsResponse> findAll() {
        List<Review> allReviews = reviewRepository.findAll();

        return allReviews.stream().map(review -> FindAllReviewsResponse.builder()
                .prato(review.getPrato())
                .nota(review.getNota())
                .estabelecimento(review.getEstabelecimento())
                .usuario(review.getUsuario().getNome())
                .mensagem(review.getMensagem())
                .tipoPrato(review.getTipoPrato())
                .build())
                .toList();
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
