package com.eventia.auth.domain.usecase;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.gateway.ClienteGateway;
import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final EncrypterGateway encrypterGateway;

    public Cliente guardarCliente (Cliente cliente) {
        if (cliente.getEmail() == null || cliente.getPassword() == null || cliente.getNombre() == null || cliente.getEdad() == null) {
            throw new NullPointerException("Ojo con eso, campos vacios");

        }
        if(cliente.getNombre() == null && cliente.getPassword() == null){
            throw new IllegalArgumentException("El nombre y la contraseña son obligatorios");
        }
        if (cliente.getEdad()<18) {
            System.out.println("Necesitas ser mayor de 18");
        }
        String passwordEncrypt =encrypterGateway.encrypt(cliente.getPassword());
        cliente.setPassword(passwordEncrypt);

        return clienteGateway.guardarCliente(cliente);
    }
    public void eliminarClientePorId(Long id_Cliente) {
        try {
            clienteGateway.eliminarCliente(id_Cliente);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    public Cliente buscarClientePorId(Long id_Cliente) {
        try {
            return clienteGateway.buscarPorId(id_Cliente);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return new Cliente();
        }
    }

    public Cliente actualizarCliente(Cliente cliente) {
        if (cliente.getId_Usuario() == null) {
            throw new IllegalArgumentException("El ID es obligatorio");
        }

        String passwordEncrypt = encrypterGateway.encrypt(cliente.getPassword());
        cliente.setPassword(passwordEncrypt);

        return clienteGateway.actualizarCliente(cliente);
    }
    public boolean cambiarEstadoCuenta(Long id_Cliente){
        try {
            return clienteGateway.cambiarEstadoCuenta(id_Cliente);
        }catch (Exception error){
            System.out.println(error.getMessage());
            return  false;
        }
    }

}
