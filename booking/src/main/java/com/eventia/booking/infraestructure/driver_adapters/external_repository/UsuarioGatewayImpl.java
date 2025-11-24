package com.eventia.booking.infraestructure.driver_adapters.external_repository;

import com.eventia.booking.domain.model.Rol;
import com.eventia.booking.domain.model.gateway.UsuarioGateway;
import com.eventia.booking.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@RequiredArgsConstructor
@Component
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final RestTemplate restTemplate;

    @Override
    public boolean autorizacion(Long id_Usuario, Rol rol){

        try {
            ResponseEntity<UsuarioData>  usuario = restTemplate.getForEntity("https://eventia-project.onrender.com/api/auth/" + id_Usuario, UsuarioData.class);
            if(usuario.getBody().getRol() == rol){
                return true;
            }
                return false;
        } catch (HttpClientErrorException.NotFound e){
            return false;
        } catch (Exception errorMensaje){
            throw new RuntimeException("Error al colsuntar el servicio de Autenticacion", errorMensaje);
        }

    }

}
