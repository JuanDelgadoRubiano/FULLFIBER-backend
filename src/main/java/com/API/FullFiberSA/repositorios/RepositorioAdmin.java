package com.API.FullFiberSA.repositorios;

import com.API.FullFiberSA.modelos.Administradores;
import com.API.FullFiberSA.modelos.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RepositorioAdmin extends JpaRepository<Administradores, Long> {

    // METODO PARA ENCONTRAR EL ADMINISTRADOR A PARTIR DE SU NOMBRE
    @Transactional(readOnly=true)
    @Query("FROM Administradores a WHERE a.nombreAdmin = :nombre")
    Administradores encontrarAdminByNombre(@Param("nombre") String nombre);
}
