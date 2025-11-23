package com.eventia.review.domain.model;


import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Review {
    private Long id_reseña;
    private Long id_Reserva;
    private Long id_Cliente;
    private Long id_Servicio;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
