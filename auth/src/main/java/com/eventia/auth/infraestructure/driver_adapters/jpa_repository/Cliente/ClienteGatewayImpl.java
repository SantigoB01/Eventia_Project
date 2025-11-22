package com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.gateway.ClienteGateway;
import com.eventia.auth.infraestructure.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteMapper clienteMapper;
    private final ClienteDataJpaRepository repository;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        ClienteData clienteData = clienteMapper.toData(cliente);
        return clienteMapper.toCliente(repository.save(clienteData));
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        repository.deleteById(idCliente);
    }

    @Override
    public Cliente buscarPorId(Long idCliente) {
        return repository.findById(idCliente)
                .map(clienteMapper::toCliente)
                .orElse(null);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        if (!repository.existsById(cliente.getId_Usuario())) {
            return null;
        }
        ClienteData data = clienteMapper.toData(cliente);
        return clienteMapper.toCliente(repository.save(data));
    }

    @Override
    public boolean cambiarEstadoCuenta(Long idCliente) {
        ClienteData clienteData = repository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("El cliente no existe"));

        boolean nuevoEstado = !clienteData.getActivo();
        clienteData.setActivo(nuevoEstado);
        repository.save(clienteData);

        return nuevoEstado;
    }
}
