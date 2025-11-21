package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Cliente;

public interface ClienteGateway {
    Cliente guardarCliente(Cliente cliente);
    void eliminarCliente(Long id_Cliente);
    Cliente buscarPorId(Long id_Cliente);
    Cliente actualizarCliente(Cliente cliente);
    boolean cambiarEstadoCuenta(Long id_Cliente);
}
