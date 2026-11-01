package com.inventario.gestion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "productos")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor 
@Getter 
@Setter 
@Builder 
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 125)
    private String nombre;

    @Column(nullable = false)
    private Long precio;

    @Column(nullable = false)
    private Long stock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_producto_id", nullable = false) 
    private TipoProducto tipoProducto;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        LocalDateTime ahora = LocalDateTime.now();

        this.fechaCreacion = ahora;
        this.fechaActualizacion = ahora;
    }

    @PreUpdate
    public void preUpdate(){
        this.fechaActualizacion = LocalDateTime.now();
    }
}
