package com.eventia.auth.infraestructure.mapper;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente.ClienteData;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toCliente(ClienteData clienteData){
        return new Cliente(
                clienteData.getTelefono(),
                clienteData.getActivo(),
                clienteData.getReseñaH()

        );
    }

    public ClienteData toData(Cliente cliente){
        return new ClienteData(
                cliente.getTelefono(),
                cliente.getActivo(),
                cliente.getReseñasH()
        );
    }
}

