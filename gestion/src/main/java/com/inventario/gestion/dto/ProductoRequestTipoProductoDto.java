package com.inventario.gestion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoRequestTipoProductoDto(

    @NotNull(message = "El id del producto es obligatorio")
    @Min(1)
    Long idProducto,


    @NotBlank(message = "El tipo de producto es obligatorio")
    @Size(max = 125, message = "El nombre del tipo producto debe tener como máximo 125 caracteres")
    String nombreTipoProducto

) {}
