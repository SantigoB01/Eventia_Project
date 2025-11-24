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
        model.setIdresena(entity.getIdresena());
        model.setIdReserva(entity.getIdReserva());
        model.setIdCliente(entity.getIdCliente());
        model.setIdServicio(entity.getIdServicio());
        model.setRating(entity.getRating());
        model.setComment(entity.getComment());
        model.setCreatedAt(entity.getCreatedAt());
        return model;
    }

    public ReviewData toEntity(Review model) {
        if (model == null) return null;

        ReviewData entity = new ReviewData();
        entity.setIdresena(model.getIdresena());
        entity.setIdReserva(model.getIdReserva());
        entity.setIdCliente(model.getIdCliente());
        entity.setIdServicio(model.getIdServicio());
        entity.setRating(model.getRating());
        entity.setComment(model.getComment());
        entity.setCreatedAt(model.getCreatedAt());
        return entity;
    }
}
