package com.eventia.auth.infraestructure.mapper;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Cliente.ClienteData;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toCliente(ClienteData clienteData){
        Cliente cliente = new Cliente();

        cliente.setId_Usuario(clienteData.getId_Usuario());
        cliente.setNombre(clienteData.getNombre());
        cliente.setEmail(clienteData.getEmail());
        cliente.setPassword(clienteData.getPassword());
        cliente.setRol(clienteData.getRol());
        cliente.setEdad(clienteData.getEdad());
        cliente.setCiudad(clienteData.getCiudad());

        cliente.setTelefono(clienteData.getTelefono());
        cliente.setActivo(clienteData.getActivo());
        cliente.setResenasH(clienteData.getResenaH());

        return cliente;
    }

    public ClienteData toData(Cliente cliente){
        ClienteData data = new ClienteData();

        data.setId_Usuario(cliente.getId_Usuario());
        data.setNombre(cliente.getNombre());
        data.setEmail(cliente.getEmail());
        data.setPassword(cliente.getPassword());
        data.setRol(cliente.getRol());
        data.setEdad(cliente.getEdad());
        data.setCiudad(cliente.getCiudad());

        data.setTelefono(cliente.getTelefono());
        data.setActivo(cliente.getActivo());
        data.setResenaH(cliente.getResenasH());

        return data;
    }
}
