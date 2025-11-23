package com.eventia.review.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewData, Long> {

    List<ReviewData> findByArtistId(Long id_Servicio);

    boolean existsByReservationIdAndClientIdAndAllowedTrue(Long reservationId, Long clientId);

    void allowReview(Long reservationId, Long clientId, Long artistId);

    List<ReviewData> findByClientId(Long clientId);

}
