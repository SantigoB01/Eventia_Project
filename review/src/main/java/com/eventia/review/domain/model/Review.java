package com.eventia.review.domain.model;


import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Review {
    private Long idresena;
    private Long idReserva;
    private Long idCliente;
    private Long idServicio;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
