package com.inventario.gestion.service;

import java.util.List;

import com.inventario.gestion.dto.ProductoRequesDto;
import com.inventario.gestion.dto.ProductoResponseDto;

public interface ProductoService {

    List<ProductoResponseDto> obtenerTodos();

    ProductoResponseDto obtenerPorId(Long id);

    ProductoResponseDto crear(ProductoRequesDto dto);
    
    void borrar(Long id);

}
