package com.eventia.review.domain.usecase;

import com.eventia.review.domain.exceptions.ClienteNoEncontradoException;
import com.eventia.review.domain.exceptions.ReservaNoexisteException;
import com.eventia.review.domain.exceptions.ReviewNotAllowedException;
import com.eventia.review.domain.exceptions.ServcioNoExiste;
import com.eventia.review.domain.model.gateway.ClienteGateway;
import com.eventia.review.domain.model.gateway.ReservaGateway;
import com.eventia.review.domain.model.gateway.ReviewGateway;
import com.eventia.review.domain.model.Review;
import com.eventia.review.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ReviewUseCase {

    private final ReviewGateway gateway;
    private final ClienteGateway clienteGateway;
    private final ReservaGateway reservaGateway;
    private final ServicioGateway servicioGateway;

    public Review create(Review review) {

        if(!clienteGateway.clienteExiste(review.getIdCliente())){
            throw new ClienteNoEncontradoException("Cliente no existente.");
        }

        if (!reservaGateway.reservaExiste(review.getIdReserva())){
            throw new ReservaNoexisteException("Reserva no hecha y/o inexitente.");
        }

        if (!servicioGateway.servicioExiste(review.getIdServicio())){
            throw new ServcioNoExiste("Servicio no encontrado");
        }

        if (!gateway.tienePermitido(review.getIdReserva(), review.getIdCliente())) {
            throw new ReviewNotAllowedException("El cliente no puede reseñar esta reserva.");
        }

        review.setCreatedAt(LocalDateTime.now());

        return gateway.save(review);
    }

    public List<Review> listByArtist(Long artistId) {
        return gateway.findById_Servicio(artistId);
    }

    // Evento desde Booking para habilitar reseñas:
    public void allowReview(Long reservationId, Long clientId, Long artistId) {
        gateway.allowReview(reservationId, clientId, artistId);
    }

    public Review update(Long id, Review newReviewData) {
        Review existing = gateway.findById(id);
        if (existing == null) throw new RuntimeException("Reseña no encontrada.");

        existing.setRating(newReviewData.getRating());
        existing.setComment(newReviewData.getComment());

        return gateway.update(existing);
    }

    public void delete(Long id) {
        Review existing = gateway.findById(id);
        if (existing == null) throw new RuntimeException("Reseña no encontrada.");

        gateway.delete(id);
    }

    public List<Review> listByClient(Long clientId) {
        return gateway.findById_Cliente(clientId);
    }

}
