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
    public Cliente registroCliente(Cliente cliente) {
        ClienteData clienteData = clienteMapper.toData(cliente);
        return clienteMapper.toCliente(repository.save(clienteData));
    }

    @Override
    public Cliente verPerfil(Cliente cliente) {
        return repository.findById(cliente.getId_Usuario())
                .map(clienteData -> clienteMapper.toCliente(clienteData))
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        return null;
    }

    @Override
    public boolean desactivarCuenta(Long id_Cliente) {
        return false;
    }
}
