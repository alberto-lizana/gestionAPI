package com.inventario.gestion.mappers;

import com.inventario.gestion.dto.RolResponseDto;
import com.inventario.gestion.model.Rol;

public class RolMapper {

    public static RolResponseDto toDto(Rol r){
        return RolResponseDto
            .builder()
                .idRol(r.getId())
                .nombre(r.getNombre())
            .build();
    }

}
