package com.inventario.gestion.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventario.gestion.model.Producto;

@Repository 
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    boolean existsByNombre(String nombre);

    @Query(
        value = """
            SELECT p
            FROM Producto p
            LEFT JOIN FETCH p.tipoProducto
        """,
        countQuery = """
            SELECT COUNT(p)
            FROM Producto p
        """
    )
    Page<Producto> obtenerTodos(Pageable pageable);

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
