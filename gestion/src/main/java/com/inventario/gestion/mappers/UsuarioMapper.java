package com.inventario.gestion.mappers;

import com.inventario.gestion.dto.UsuarioResponseDto;
import com.inventario.gestion.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDto toDto(Usuario us){
        return UsuarioResponseDto
            .builder()
                .id(us.getId())
                .username(us.getUsername())
                .nombre(us.getNombre())
                .appat(us.getAppat())
                .email(us.getEmail())
                .isActive(us.getIsActive())
                .asignacionesRoles(us.getAsignacionesRoles().stream().map(UsuarioRolMapper::toDto).toList())
            .build();
    }

}
