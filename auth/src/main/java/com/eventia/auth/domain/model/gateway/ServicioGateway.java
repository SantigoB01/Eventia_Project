package com.eventia.auth.domain.model.gateway;

public interface ServicioGateway {
    String obtenerCategoria(String nombreC);
    String crearCategoria(String nombreC);
    String eliminarCategoria(String nombreC);
}
