package com.inventario.gestion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tipo_producto")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor 
@Getter 
@Setter 
@Builder 
public class TipoProducto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 125)
    private String nombre;

    @Column(length = 255)
    private String descripcion;
    
}
