package com.inventario.gestion.mappers;

import com.inventario.gestion.dto.UsuarioRolResponseDto;
import com.inventario.gestion.model.UsuarioRol;

public class UsuarioRolMapper {

    public static UsuarioRolResponseDto toDto(UsuarioRol ur){
        return UsuarioRolResponseDto
            .builder()
                .rol(RolMapper.toDto(ur.getRol()))
            .build();
    }
}
