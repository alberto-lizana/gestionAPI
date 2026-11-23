package com.inventario.gestion.service;

import java.util.List;

import com.inventario.gestion.dto.ProductoRequesDto;
import com.inventario.gestion.dto.ProductoRequestNombreDto;
import com.inventario.gestion.dto.ProductoRequestPrecioDto;
import com.inventario.gestion.dto.ProductoRequestStockDto;
import com.inventario.gestion.dto.ProductoRequestTipoProductoDto;
import com.inventario.gestion.dto.ProductoResponseDto;

public interface ProductoService {

    List<ProductoResponseDto> obtenerTodos();

    ProductoResponseDto obtenerPorId(Long id);

    ProductoResponseDto crear(ProductoRequesDto dto);
    
    void borrar(Long id);

    ProductoResponseDto modificarNombre(ProductoRequestNombreDto dto);

    ProductoResponseDto modificarPrecio(ProductoRequestPrecioDto dto);

    ProductoResponseDto modificarStock(ProductoRequestStockDto dto);
    
    ProductoResponseDto modificarTipoProducto(ProductoRequestTipoProductoDto dto);

}
