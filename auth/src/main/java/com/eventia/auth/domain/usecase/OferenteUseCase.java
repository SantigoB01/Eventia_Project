package com.eventia.auth.domain.usecase;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.Notificacion;
import com.eventia.auth.domain.model.Oferente;
import com.eventia.auth.domain.model.gateway.EncrypterGateway;
import com.eventia.auth.domain.model.gateway.NotificationGateway;
import com.eventia.auth.domain.model.gateway.OferenteGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OferenteUseCase {
    private final OferenteGateway oferenteGateway;
    private final EncrypterGateway encrypterGateway;
    private final NotificationGateway notificationGateway;

    public Oferente guardarOferente (Oferente oferente) {
        if (oferente.getEmail() == null || oferente.getPassword() == null || oferente.getNombre() == null || oferente.getEdad() == null) {
            throw new NullPointerException("Ojo con eso, campos vacios");

        }
        if(oferente.getNombre() == null && oferente.getPassword() == null){
            throw new IllegalArgumentException("El nombre y la contraseña son obligatorios");
        }
        if (oferente.getEdad()<18) {
            System.out.println("Necesitas ser mayor de 18");
        }
        String passwordEncrypt =encrypterGateway.encrypt(oferente.getPassword());
        oferente.setPassword(passwordEncrypt);

        Oferente usuarioGuardado = oferenteGateway.guardarOferente(oferente);

        Notificacion mensajeNotificacion = Notificacion.builder()
                .tipo("Registro Cliente")
                .email(usuarioGuardado.getEmail())
                .telefono(usuarioGuardado.getTelefono())
                .mensaje(" Usuario registrado con exito")
                .build();

        try{notificationGateway.enviarMensaje(mensajeNotificacion);}
        catch(Exception e){
            System.out.println("Error al enviar mensaje");
            System.out.println(e.getMessage());
            return usuarioGuardado;
        }
        return usuarioGuardado;
    }
    public void eliminarOferentePorId(Long id_Oferente) {
        try {
            oferenteGateway.eliminarOferente(id_Oferente);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    public Oferente buscarOferentePorId(Long id_Oferente) {
        try {
            return oferenteGateway.buscarPorId(id_Oferente);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return new Oferente();
        }
    }

    public Oferente actualizarOferente(Oferente oferente) {
        if (oferente.getId_Usuario() == null) {
            throw new IllegalArgumentException("El ID es obligatorio");
        }

        String passwordEncrypt = encrypterGateway.encrypt(oferente.getPassword());
        oferente.setPassword(passwordEncrypt);

        return oferenteGateway.actualizarOferente(oferente);
    }
    public boolean cambioEstadoCuenta(Long id_Oferente) {
        try {
            return oferenteGateway.cambioEstadoCuenta(id_Oferente);
        }catch (Exception error){
            System.out.println(error.getMessage());
            return  false;
        }
    }

}
