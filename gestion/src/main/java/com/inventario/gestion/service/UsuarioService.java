package com.inventario.gestion.service;

import java.util.List;

import com.inventario.gestion.dto.UsuarioRequestDto;
import com.inventario.gestion.dto.UsuarioResponseDto;

public interface UsuarioService {

    List<UsuarioResponseDto> obtenerTodos();

    UsuarioResponseDto obtenerPorId(Long id);

    UsuarioResponseDto obtenerPorUsername(Object principal);

    UsuarioResponseDto crear(UsuarioRequestDto dto);

    UsuarioResponseDto borrarLogico(Long id);

    void borrarPorId(Long id);

}