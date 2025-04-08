package org.dishdiary.service;

import org.dishdiary.domain.responses.FindAllReviewsResponse;
import org.dishdiary.domain.reviews.Review;
import org.dishdiary.domain.reviews.ReviewRepository;
import org.dishdiary.enums.FoodTypeEnum;
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

        return allReviews.stream().map(this::convertReviewToResponse).toList();
    }

    public List<FindAllReviewsResponse> findByFoodType(FoodTypeEnum foodType) {
        List<Review> allReviews = reviewRepository.findByTipoPrato(foodType.name());

        return allReviews.stream().map(this::convertReviewToResponse).toList();
    }

    public int save(Review review) {
        Review entitySavedInDB = reviewRepository.save(review);

        return entitySavedInDB.getId();
    }

    public List<FindAllReviewsResponse> findReviewsByEstablishment(String establishment) {
        List<Review> allReviews = reviewRepository.findByEstabelecimento(establishment);

        if (allReviews.isEmpty()) {
            return new ArrayList<>();
        }

        return allReviews.stream().map(this::convertReviewToResponse).toList();
    }

    public List<Review> findReviewsByCpf(String cpf) {
        return reviewRepository.findByCpf(cpf);
    }

    private FindAllReviewsResponse convertReviewToResponse(Review review) {
        return FindAllReviewsResponse.builder()
                .prato(review.getPrato())
                .nota(review.getNota())
                .estabelecimento(review.getEstabelecimento())
                .usuario(review.getUsuario().getNome())
                .mensagem(review.getMensagem())
                .tipoPrato(review.getTipoPrato())
                .build();
    }
}
