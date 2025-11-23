package com.eventia.review.infraestructure.mapper;


import com.eventia.review.domain.model.Review;
import com.eventia.review.infraestructure.driver_adapters.jpa_repository.ReviewData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewMapper {

    public Review toModel(ReviewData entity) {
        if (entity == null) return null;

        Review model = new Review();
        model.setId_reseña(entity.getId_reseña());
        model.setId_Reserva(entity.getId_Reserva());
        model.setId_Cliente(entity.getId_Cliente());
        model.setId_Servicio(entity.getId_Servicio());
        model.setRating(entity.getRating());
        model.setComment(entity.getComment());
        model.setCreatedAt(entity.getCreatedAt());
        return model;
    }

    public ReviewData toEntity(Review model) {
        if (model == null) return null;

        ReviewData entity = new ReviewData();
        entity.setId_reseña(model.getId_reseña());
        entity.setId_Reserva(model.getId_Reserva());
        entity.setId_Cliente(model.getId_Cliente());
        entity.setId_Servicio(model.getId_Servicio());
        entity.setRating(model.getRating());
        entity.setComment(model.getComment());
        entity.setCreatedAt(model.getCreatedAt());
        return entity;
    }
}
