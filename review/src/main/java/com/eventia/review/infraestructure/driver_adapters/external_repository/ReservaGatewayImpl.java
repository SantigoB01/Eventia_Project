package com.eventia.review.infraestructure.driver_adapters.external_repository;

import com.eventia.review.domain.model.gateway.ReservaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class ReservaGatewayImpl implements ReservaGateway {

    private final RestTemplate restTemplate;

    @Override
    public boolean reservaExiste(Long idReserva){

        try {
            restTemplate.getForEntity("https://eventia-project.onrender.com/api/bookings/reserva/" + idReserva, void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e){
            return false;
        } catch (Exception errorMensaje){
            throw new RuntimeException("Error al colsuntar el servicio de Autenticacion", errorMensaje);
        }

    }
}
