package com.inventario.gestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventario.gestion.model.TipoProducto;


@Repository 
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    boolean existsByNombre(String nombre);

    @Query
    ("""
        SELECT tp 
        FROM TipoProducto tp
        WHERE tp.nombre = :tipoProductoNombre
    """)
    Optional<TipoProducto> obtenerPorNombre(@Param("tipoProductoNombre") String tipoProductoNombre);

    @Modifying 
    @Query
    ("""
        DELETE 
        FROM TipoProducto tp
        WHERE tp.id = :tipoProductoId
    """)
    int deleteByIdTipoProducto(@Param("tipoProductoId") Long tipoProductoId);
}
