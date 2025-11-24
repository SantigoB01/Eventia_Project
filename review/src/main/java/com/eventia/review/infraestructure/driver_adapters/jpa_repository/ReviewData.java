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
    private Long idresena;
    private Long idReserva;
    private Long idCliente;
    private Long idServicio;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

}
