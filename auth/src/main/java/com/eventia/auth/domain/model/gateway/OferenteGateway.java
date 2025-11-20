package com.eventia.auth.domain.model.gateway;

import com.eventia.auth.domain.model.Cliente;
import com.eventia.auth.domain.model.Oferente;
import com.eventia.auth.domain.model.Usuario;

public interface OferenteGateway {

    Oferente guardarOferente(Oferente oferente);
    void eliminarUsuario(Long id_Usuario);
    Oferente buscarPorId(Long id_Usuario);
    Oferente actualizarUsuarioOferente(Oferente oferente);
    Oferente verPerfil(Oferente oferente);
    Oferente editarperfilOferente(Oferente oferente);
    boolean desactivarCuenta(Long id_Oferente);
}
