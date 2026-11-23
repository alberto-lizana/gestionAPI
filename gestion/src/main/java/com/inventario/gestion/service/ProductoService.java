package com.inventario.gestion.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventario.gestion.dto.ProductoModificarDto;
import com.inventario.gestion.dto.ProductoRequesDto;
import com.inventario.gestion.dto.ProductoRequestNombreDto;
import com.inventario.gestion.dto.ProductoRequestPrecioDto;
import com.inventario.gestion.dto.ProductoRequestStockDto;
import com.inventario.gestion.dto.ProductoRequestTipoProductoDto;
import com.inventario.gestion.dto.ProductoResponseDto;

public interface ProductoService {

    Page<ProductoResponseDto> obtenerTodos(Pageable pageable);

    ProductoResponseDto obtenerPorId(Long id);

    ProductoResponseDto crear(ProductoRequesDto dto);

    ProductoResponseDto modificar(ProductoModificarDto dto);
    
    void borrar(Long id);

    ProductoResponseDto modificarNombre(ProductoRequestNombreDto dto);

    ProductoResponseDto modificarPrecio(ProductoRequestPrecioDto dto);

    ProductoResponseDto modificarStock(ProductoRequestStockDto dto);
    
    ProductoResponseDto modificarTipoProducto(ProductoRequestTipoProductoDto dto);

}
