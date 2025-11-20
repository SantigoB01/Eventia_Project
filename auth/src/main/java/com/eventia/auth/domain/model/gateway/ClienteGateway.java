package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Cliente;

public interface ClienteGateway {
    public Cliente registroCliente(Cliente cliente);
    public Cliente verPerfil(Cliente cliente);
    public Cliente editarCliente(Cliente cliente);
    public boolean desactivarCuenta(Long id_Cliente);
}
