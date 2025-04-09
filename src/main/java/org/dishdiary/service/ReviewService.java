package org.dishdiary.service;

import org.dishdiary.domain.responses.FindAllReviewsResponse;
import org.dishdiary.domain.reviews.Review;
import org.dishdiary.domain.reviews.ReviewRepository;
import org.dishdiary.domain.users.User;
import org.dishdiary.enums.FoodTypeEnum;
import org.dishdiary.utils.FormatterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    public List<FindAllReviewsResponse> findAll(String cpf) {
        List<Review> allReviews = reviewRepository.findAll(cpf);

        return allReviews.stream().map(this::convertReviewToResponse).toList();
    }

    public List<FindAllReviewsResponse> findByFoodType(FoodTypeEnum foodType, String cpf) {
        List<Review> allReviews = reviewRepository.findByFoodType(foodType.name(), cpf);

        return allReviews.stream().map(this::convertReviewToResponse).toList();
    }

    public int save(Review review) {
        User user = userService.findByCpf(review.getUsuario().getCpf());
        review.setUsuario(user);
        review.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        Review entitySavedInDB = reviewRepository.save(review);

        return entitySavedInDB.getId();
    }

    public List<FindAllReviewsResponse> findReviewsByEstablishment(String establishment, String cpf) {
        List<Review> allReviews = reviewRepository.findByEstablishment(establishment, cpf);

        if (allReviews.isEmpty()) {
            return new ArrayList<>();
        }

        return allReviews.stream().map(this::convertReviewToResponse).toList();
    }

    public List<FindAllReviewsResponse> findReviewsByCustomerName(String name, String cpf) {
        List<Review> allReviews = reviewRepository.findByCustomerName(name, cpf);

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
                .dataCadastro(FormatterUtils.formatLocalDateTime(review.getDataCadastro()))
                .build();
    }
}
