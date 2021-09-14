package com.API.FullFiberSA.repositorios;

import com.API.FullFiberSA.modelos.LogsDeCambio;
import com.API.FullFiberSA.proyecciones.clientesReporte;
import com.API.FullFiberSA.proyecciones.logsReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepositorioLogsDeCambio extends JpaRepository<LogsDeCambio,Long> {

    // QUERY NATIVO QUE SE ENCARGA DE ENCONTRAR TODOS LOS LOGS DE CAMBIO EN EL SISTEMA
    // PARA SER LISTADOS EN EL REPORTE
    @Transactional()
    @Query(value = " select l.id_log_cambio, l.comentario_cambio, l.fecha_cambio_log, a.nombre_admin, " +
            "        l.id_campanha_anterior, l.id_campanha_nueva, cl.nombre_cliente from\n" +
            "        logs_de_cambio l, administradores a, clientes cl where cl.id_cliente = l.id_cliente " +
            "        and l.id_admin = a.id_admin",nativeQuery = true)
    List<logsReporte> generarReporte();

}
