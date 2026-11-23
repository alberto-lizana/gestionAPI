package com.inventario.gestion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoModificarDto(

    @NotNull
    Long id,

    @NotBlank
    @Size(min = 3, max = 125)
    String nombre,

    @NotNull
    @Min(1)
    Long precio,

    @NotNull
    @Min(1)
    Long stock,

    @NotBlank
    String nombreTipoProducto

) {}