package com.eventia.auth.infraestructure.mapper;

import com.eventia.auth.domain.model.Oferente;
import com.eventia.auth.infraestructure.driver_adapters.jpa_repository.Oferente.OferenteData;
import org.springframework.stereotype.Component;

@Component
public class OferenteMapper {

    public Oferente toOferente(OferenteData oferenteData){
        return new Oferente(
                oferenteData.getTelefono(),
                oferenteData.getNombre_Artistico(),
                oferenteData.getEspecialidad(),
                oferenteData.getActivo()
        );
    }

    public OferenteData toData(Oferente oferente){
        return new OferenteData(
                oferente.getTelefono(),
                oferente.getNombre_Artistico(),
                oferente.getEspecialidad(),
                oferente.getActivo()
        );
    }
}
