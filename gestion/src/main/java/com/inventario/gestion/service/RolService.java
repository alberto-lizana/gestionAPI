package com.inventario.gestion.service;

import java.util.List;

import com.inventario.gestion.dto.RolNombreRequestDto;
import com.inventario.gestion.dto.RolResponseDto;

public interface RolService {

    List<RolResponseDto> obtenerTodos();

    RolResponseDto obtenerPorId(Long id);

    RolResponseDto modificarNombre(RolNombreRequestDto dto);

    void eliminar(Long id);
}
