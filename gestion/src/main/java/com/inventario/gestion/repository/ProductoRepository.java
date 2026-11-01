package com.inventario.gestion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventario.gestion.model.Producto;

@Repository 
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    boolean existsByNombre(String nombre);

    @Query
    ("""
        SELECT p 
        FROM Producto p 
        LEFT JOIN FETCH p.tipoProducto
    """)
    List<Producto> obtenerTodos();

    @Query
    ("""
        SELECT p
        FROM Producto p
        LEFT JOIN FETCH p.tipoProducto
        WHERE p.id = :productoId
    """)
    Optional<Producto> obtenerPorId(@Param("productoId") Long productoId);

    @Modifying 
    @Query
    ("""
        DELETE 
        FROM Producto p 
        WHERE p.id = :productoId
    """)
    int borrarPorId(@Param("productoId") Long productoId);

}
