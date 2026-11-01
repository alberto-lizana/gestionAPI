package com.inventario.gestion.mappers;

import com.inventario.gestion.dto.TipoProductoResponseDto;
import com.inventario.gestion.model.TipoProducto;

public class TipoProductoMapper {

    public static TipoProductoResponseDto toDto(TipoProducto tp){
        return TipoProductoResponseDto
            .builder()
                .id(tp.getId())
                .nombre(tp.getNombre())
                .descripcion(tp.getDescripcion())
            .build();
    }

}
