package com.eventia.review.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "allowed_reviews")
public class AllowedReviewData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reservationId;

    private Long clientId;

    private Long artistId;
}
