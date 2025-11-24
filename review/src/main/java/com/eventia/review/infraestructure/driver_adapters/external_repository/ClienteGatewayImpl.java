package com.eventia.review.infraestructure.driver_adapters.external_repository;

import com.eventia.review.domain.model.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class ClienteGatewayImpl implements ClienteGateway {

    private final RestTemplate restTemplate;

    @Override
    public boolean clienteExiste(Long id_Cliente){

        try {
            restTemplate.getForEntity("http://localhost:9090/api/eventia/cliente/" + id_Cliente, void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e){
            return false;
        } catch (Exception errorMensaje){
            throw new RuntimeException("Error al colsuntar el servicio de Autenticacion", errorMensaje);
        }

    }
}
