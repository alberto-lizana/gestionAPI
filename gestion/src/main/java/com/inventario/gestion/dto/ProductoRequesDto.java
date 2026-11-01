package com.inventario.gestion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoRequesDto(

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 3, max = 125, message = "El nombre debe tener entre 3 y 125 caracteres")
    String nombre,

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor que cero")
    Long precio,

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 1, message = "El stock debe ser como mínimo 1")
    @Max(value = 999, message = "El stock no puede superar las 999 unidades")
    Long stock,

    @NotBlank(message = "El tipo de producto es obligatorio")
    String nombreTipoProducto

) {}
