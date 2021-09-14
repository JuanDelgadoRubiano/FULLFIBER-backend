package com.API.FullFiberSA.repositorios;

import com.API.FullFiberSA.modelos.Clientes;
import com.API.FullFiberSA.modelos.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioProductos extends JpaRepository<Productos,Long> {

    // QUERY QUE CONSULTA TODOS LOS PRODUCTOS A PARTIR DE UN TIPO
    @Query("FROM Productos acc WHERE acc.tipoProducto.idTipo = :tipo")
    List<Productos> encontrarProductosByIdTipo(@Param("tipo") Long tipo);

}
