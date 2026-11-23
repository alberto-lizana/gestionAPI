package com.inventario.gestion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductoRequestPrecioDto(

    @NotNull(message = "El id del producto es obligatorio")
    @Min(1)
    Long idProducto,

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor que cero")
    Long precio

) {}
