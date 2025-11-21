package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.gateway.ClienteGateway;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente.OferenteData;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente.OferenteDataJpaRepository;
import com.eventia.auth.infraestructure.mapper.ClienteMapper;
import com.eventia.auth.infraestructure.mapper.OferenteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteDataJpaRepository repository;
    private final ClienteMapper clienteMapper;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return null;
    }

    @Override
    public void eliminarCliente(Long id_Cliente) {

    }

    @Override
    public Cliente buscarPorId(Long id_Cliente) {
        return null;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return null;
    }


    @Override
    public boolean cambiarEstadoCuenta(Long id_Cliente) {
        return false;
    }
}
