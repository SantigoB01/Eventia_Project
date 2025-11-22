package com.eventia.auth.infraestructure.mapper;

import com.eventia.auth.domain.model.Oferente;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente.OferenteData;
import org.springframework.stereotype.Component;

@Component
public class OferenteMapper {

    public Oferente toOferente(OferenteData oferenteData){
        Oferente oferente = new Oferente();

        oferente.setId_Usuario(oferenteData.getId_Usuario());
        oferente.setNombre(oferenteData.getNombre());
        oferente.setEmail(oferenteData.getEmail());
        oferente.setPassword(oferenteData.getPassword());
        oferente.setRol(oferenteData.getRol());
        oferente.setEdad(oferenteData.getEdad());
        oferente.setCiudad(oferenteData.getCiudad());

        oferente.setNombre_Artistico(oferenteData.getNombreArtistico());
        oferente.setEspecialidad(oferenteData.getEspecialidad());
        oferente.setTelefono(oferenteData.getTelefono());
        oferente.setActivo(oferenteData.getActivo());

        return oferente;
    }

    public OferenteData toData(Oferente oferente){
        OferenteData data = new OferenteData();

        data.setId_Usuario(oferente.getId_Usuario());
        data.setNombre(oferente.getNombre());
        data.setEmail(oferente.getEmail());
        data.setPassword(oferente.getPassword());
        data.setRol(oferente.getRol());
        data.setEdad(oferente.getEdad());
        data.setCiudad(oferente.getCiudad());
        data.setTelefono(oferente.getTelefono());
        data.setNombreArtistico(oferente.getNombre_Artistico());
        data.setEspecialidad(oferente.getEspecialidad());
        data.setActivo(oferente.getActivo());

        return data;
    }
}
