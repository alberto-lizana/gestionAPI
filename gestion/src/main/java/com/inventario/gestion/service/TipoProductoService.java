package com.inventario.gestion.service;

import java.util.List;

import com.inventario.gestion.dto.TipoProductoRequestDto;
import com.inventario.gestion.dto.TipoProductoResponseDto;

public interface TipoProductoService {

    List<TipoProductoResponseDto> obtenerTodos();

    TipoProductoResponseDto obtenerPorId(Long id);
    
    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    void borrar(Long id);

}
