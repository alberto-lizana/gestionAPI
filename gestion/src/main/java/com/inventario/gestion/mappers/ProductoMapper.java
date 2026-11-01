package com.inventario.gestion.mappers;

import com.inventario.gestion.dto.ProductoResponseDto;
import com.inventario.gestion.model.Producto;

public class ProductoMapper {

    public static ProductoResponseDto toDto(Producto p){
        return ProductoResponseDto
            .builder()
                .id( p.getId() != null ? p.getId() : null)
                .nombre(p.getNombre())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .tipoProducto(TipoProductoMapper.toDto(p.getTipoProducto()))
                .fechaCreacion(p.getFechaCreacion())
                .fechaActualizacion(p.getFechaActualizacion())
            .build();
    }

}
