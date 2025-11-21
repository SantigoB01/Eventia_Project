package com.eventia.auth.infraestructure.driver_adapters.external_repository;

import com.eventia.auth.domain.model.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class ServicioGatewayImpl implements ServicioGateway {

    //private final RestTemplate restTemplate;

  /*  @Override
    public boolean usuarioExiste(Long usuarioId) {
        try {
            restTemplate.getForEntity("http://localhost:9090/api/ecommerce/usuario/" + usuarioId, Void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception errorMensaje) {
            throw new RuntimeException("Error al consultar el servicio de autenticación", errorMensaje);
        }
    }*/

    @Override
    public String obtenerCategoria(String nombreC) {
        return "";
    }

    @Override
    public String crearCategoria(String nombreC) {
        return "";
    }

    @Override
    public String eliminarCategoria(String nombreC) {
        return "";
    }
}
