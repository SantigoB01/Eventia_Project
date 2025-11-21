package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Notificacion;

public interface NotificationGateway {
    void enviarMensaje(Notificacion mensajeCola);
}
