package com.eventia.review.infraestructure.driver_adapters.external_repository;

import com.eventia.review.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class ServicioGatewayImpl implements ServicioGateway {

    private final RestTemplate restTemplate;

    @Override
    public boolean servicioExiste(Long idServicio){

        try {
            restTemplate.getForEntity("https://eventia-project.onrender.com/api/servicios/verServicio/" + idServicio, void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e){
            return false;
        } catch (Exception errorMensaje){
            throw new RuntimeException("Error al colsuntar el servicio de Autenticacion", errorMensaje);
        }

    }
}
