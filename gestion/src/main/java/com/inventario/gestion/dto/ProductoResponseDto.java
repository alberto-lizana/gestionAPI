package com.inventario.gestion.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder 
public record ProductoResponseDto(

    Long id,
    String nombre,
    Long precio,
    Long stock,
    TipoProductoResponseDto tipoProducto,
    LocalDateTime fechaCreacion,
    LocalDateTime fechaActualizacion

) {}
