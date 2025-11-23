package com.eventia.review.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AllowedReviewJpaRepository extends JpaRepository<AllowedReviewData, Long> {

    boolean existsByReservationIdAndClientId(Long reservationId, Long clientId);
}
