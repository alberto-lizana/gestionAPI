package com.inventario.gestion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductoRequestStockDto(

    @NotNull(message = "El id del producto es obligatorio")
    @Min(1)
    Long idProducto,

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 1, message = "El stock debe ser como mínimo 1")
    @Max(value = 999, message = "El stock no puede superar las 999 unidades")
    Long stock

) {}
