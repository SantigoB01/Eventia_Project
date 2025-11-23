package com.eventia.auth.domain.usecase;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.Notificacion;
import com.eventia.auth.domain.model.Rol;
import com.eventia.auth.domain.model.gateway.ClienteGateway;
import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import com.eventia.auth.domain.model.gateway.NotificationGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final EncrypterGateway encrypterGateway;
    private final NotificationGateway notificationGateway;

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
        cliente.setRol(Rol.CLIENTE);

        Cliente guardarCliente = clienteGateway.guardarCliente(cliente);

        Notificacion mensajeNotificacion = Notificacion.builder()
                .tipo("Registro Cliente")
                .email(guardarCliente.getEmail())
                .telefono(guardarCliente.getTelefono())
                .mensaje(" Usuario registrado con exito")
                .build();

        try{notificationGateway.enviarMensaje(mensajeNotificacion);}
        catch(Exception e){
            System.out.println("Error al enviar mensaje");
            System.out.println(e.getMessage());
            return guardarCliente;
        }

        return guardarCliente;
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

        Cliente actualizarCliente = clienteGateway.actualizarCliente(cliente);

        Notificacion mensajeNotificacion = Notificacion.builder()
                .tipo("Actualizar Cliente")
                .email(actualizarCliente.getEmail())
                .telefono(actualizarCliente.getTelefono())
                .mensaje("Actualizacion realiza con exito")
                .build();

        notificationGateway.enviarMensaje(mensajeNotificacion);

        return actualizarCliente;
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
