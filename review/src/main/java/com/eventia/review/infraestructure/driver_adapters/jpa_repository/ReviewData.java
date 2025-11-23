package com.eventia.review.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reseña;
    private Long id_Reserva;
    private Long id_Cliente;
    private Long id_Servicio;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

}
