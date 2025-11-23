package com.eventia.review.infraestructure.driver_adapters.jpa_repository;

import com.eventia.review.domain.model.Review;
import com.eventia.review.domain.model.gateway.ReviewGateway;
import com.eventia.review.infraestructure.mapper.ReviewMapper;
import com.eventia.review.infraestructure.driver_adapters.jpa_repository.ReviewData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewGatewayImpl implements ReviewGateway {

    private final ReviewJpaRepository reviewRepository;
    private final AllowedReviewJpaRepository allowedReviewRepository;
    private final ReviewMapper mapper;

    // -----------------------------------------------------
    // PERMISOS PARA CREAR RESEÑA (EVENTO DE BOOKING)
    // -----------------------------------------------------
    @Override
    public void allowReview(Long reservationId, Long clientId, Long artistId) {
        AllowedReviewData allow = new AllowedReviewData();
        allow.setReservationId(reservationId);
        allow.setClientId(clientId);
        allow.setArtistId(artistId);
        allowedReviewRepository.save(allow);
    }

    @Override
    public boolean tienePermitido(Long reservationId, Long clientId) {
        return allowedReviewRepository.existsByReservationIdAndClientId(reservationId, clientId);
    }

    // -----------------------------------------------------
    // CRUD RESEÑAS
    // -----------------------------------------------------

    @Override
    public Review save(Review review) {
        ReviewData entity = mapper.toEntity(review);
        ReviewData saved = reviewRepository.save(entity);
        return mapper.toModel(saved);
    }

    @Override
    public Review update(Review review) {
        ReviewData entity = mapper.toEntity(review);
        ReviewData updated = reviewRepository.save(entity); // save = upsert
        return mapper.toModel(updated);
    }

    @Override
    public void delete(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<Review> findByArtistId(Long artistId) {
        return reviewRepository.findByArtistId(artistId)
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public List<Review> findByClientId(Long clientId) {
        return reviewRepository.findByClientId(clientId)
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }
}