package com.eventia.booking.domain.model.gateway;

import com.eventia.booking.domain.model.Rol;

public interface UsuarioGateway {
    boolean autorizacion (Long id_Usuario, Rol rol);
}
