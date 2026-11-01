package com.inventario.gestion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record TipoProductoResponseDto(

    Long id,
    String nombre,
    String descripcion

) {}
