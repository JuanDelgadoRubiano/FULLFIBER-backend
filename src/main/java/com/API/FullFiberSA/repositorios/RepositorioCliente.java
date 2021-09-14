package com.API.FullFiberSA.repositorios;

import com.API.FullFiberSA.modelos.ClienteAplicaCampanha;
import com.API.FullFiberSA.modelos.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface RepositorioCliente extends JpaRepository<Clientes, Long> {

    // BUSQUEDA DE UN CLIENTE A PARTIR DE SU DOCUMENTO DE IDENTIDAD
    @Transactional(readOnly=true)
    @Query("FROM Clientes cl WHERE cl.documentoCliente = :documento")
    Clientes encontrarClienteByDocumento(@Param("documento") int documento);


}
