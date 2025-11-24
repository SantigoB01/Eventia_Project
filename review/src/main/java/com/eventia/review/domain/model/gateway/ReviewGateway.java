package com.eventia.review.domain.model.gateway;

import com.eventia.review.domain.model.Review;
import java.util.List;

public interface ReviewGateway {

    void allowReview(Long reservationId, Long clientId, Long artistId);
    Review save(Review review);

    Review update(Review review);

    void delete(Long reviewId);

    List<Review> findById_Servicio(Long artistId);

    List<Review> findById_Cliente(Long clientId);

    boolean tienePermitido(Long reservationId, Long clientId);


    Review findById(Long id);
}
