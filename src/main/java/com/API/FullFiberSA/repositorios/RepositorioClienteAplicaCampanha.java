package com.API.FullFiberSA.repositorios;


import com.API.FullFiberSA.proyecciones.clientesCampanha;
import com.API.FullFiberSA.proyecciones.clientesReporte;
import com.API.FullFiberSA.modelos.ClienteAplicaCampanha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;


public interface RepositorioClienteAplicaCampanha extends JpaRepository<ClienteAplicaCampanha, Long> {


    // QUERY NATIVO PARA REALIZAR LA BUSQUEDA DE TODAS LAS CAMPAÑAS DE TELEVISION
    // A LAS QUE PUEDE APLICAR UN CLIENTE
    @Transactional()
    @Query(value = " SELECT " +
            "             campanhas.fecha_inicio_campanha, " +
            "             campanhas.fecha_final_campanha, " +
            "             tipo_producto.nombre_tipo," +
            "             campanhas.descripcion_campanha, " +
            "             campanhas.id_campanha, " +
            "             productos.nombre_producto, " +
            "             campanhas.precio_campanha from VistaClientesAplicanCampanhasTelevision" +
            "            INNER JOIN campanhas on VistaClientesAplicanCampanhasTelevision.id_campanha = campanhas.id_campanha" +
            "            INNER JOIN productos on campanhas.id_producto = productos.id_producto" +
            "            INNER JOIN tipo_producto on productos.id_tipo = tipo_producto.id_tipo" +
            "            WHERE VistaClientesAplicanCampanhasTelevision.id_cliente = :idCliente",nativeQuery = true)
    List<clientesCampanha> encontrarOtrasCampanhasTvByIdCliente(@Param("idCliente") Long idCliente);

    // QUERY NATIVO PARA ENCONTRAR TODAS LAS CAMPAÑAS DE INTERNET
    // A LAS QUE PUEDE APLICAR UN CLIENTE
    @Transactional()
    @Query(value = " SELECT  " +
            "             campanhas.fecha_inicio_campanha, " +
            "             campanhas.fecha_final_campanha, " +
            "             tipo_producto.nombre_tipo," +
            "             campanhas.descripcion_campanha, " +
            "             campanhas.id_campanha, " +
            "             productos.nombre_producto, " +
            "             campanhas.precio_campanha from VistaClientesAplicanCampanhasInternet" +
            "            INNER JOIN campanhas on VistaClientesAplicanCampanhasInternet.id_campanha = campanhas.id_campanha" +
            "            INNER JOIN productos on campanhas.id_producto = productos.id_producto" +
            "            INNER JOIN tipo_producto on productos.id_tipo = tipo_producto.id_tipo" +
            "            WHERE VistaClientesAplicanCampanhasInternet.id_cliente = :idCliente",nativeQuery = true)
    List<clientesCampanha> encontrarOtrasCampanhasInterByIdCliente(@Param("idCliente") Long idCliente);

    // QUERY NATIVO PARA ENCONTRAR TODAS LAS CAMPAÑAS DE TELEFONIA
    // A LAS QUE PUEDE APLICAR UN CLIENTE
    @Transactional()
    @Query(value = " SELECT  " +
            "             campanhas.fecha_inicio_campanha, " +
            "             campanhas.fecha_final_campanha, " +
            "             tipo_producto.nombre_tipo," +
            "             campanhas.descripcion_campanha, " +
            "             campanhas.id_campanha, " +
            "             productos.nombre_producto, " +
            "             campanhas.precio_campanha from VistaClientesAplicanCampanhasTelefono" +
            "            INNER JOIN campanhas on VistaClientesAplicanCampanhasTelefono.id_campanha = campanhas.id_campanha" +
            "            INNER JOIN productos on campanhas.id_producto = productos.id_producto" +
            "            INNER JOIN tipo_producto on productos.id_tipo = tipo_producto.id_tipo" +
            "            WHERE VistaClientesAplicanCampanhasTelefono.id_cliente = :idCliente",nativeQuery = true)
    List<clientesCampanha> encontrarOtrasCampanhasTelByIdCliente(@Param("idCliente") Long idCliente);

    // QUERY NATIVO QUE ENCUENTRA TODAS LAS CAMPAÑAS A LAS QUE APLICO UN CLIENTE A PARTIR DE SU ID
    @Transactional()
    @Query(value = "     SELECT  /*+ INDEX(cliente_aplica_campanha id_cliente)*/ campanhas.id_campanha, " +
            "            campanhas.descripcion_campanha, " +
            "            campanhas.precio_campanha,  " +
            "            productos.nombre_producto," +
            "            tipo_producto.nombre_tipo," +
            "            campanhas.fecha_inicio_campanha," +
            "            campanhas.fecha_final_campanha" +
            "            FROM  cliente_aplica_campanha " +
            "            INNER JOIN campanhas ON cliente_aplica_campanha.id_campanha  = campanhas.id_campanha" +
            "            INNER JOIN productos ON campanhas.id_producto = productos.id_producto" +
            "            INNER JOIN tipo_producto ON productos.id_tipo = tipo_producto.id_tipo" +
            "            WHERE id_cliente = :idCliente AND estado_cliente_campanha = 1 " ,nativeQuery = true)
    List<clientesCampanha> encontrarCampanhaByIdCliente(@Param("idCliente") Long idCliente);


    // LLAMADO AL PROCEDIMIENTO ALMACENADO (PROCESAMIENTO MASIVO) QUE SE ENCARGA DE ASIGNAR
    // A LOS CLIENTES SUS CAMPAÑAS CORRESPONDIENTES
    static int verificarAplicabilidadClientes() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "MISCO", "11111");
            CallableStatement cs  = con.prepareCall("{call clienteaplicacampanha.verificarAplicabilidadClientes(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            return cs.getInt(1);
        }
        catch(Exception ex){
            System.out.println(ex);
            return 0;
        }
    }

    // QUERY NATIVO QUE SE ENCARGA DE ENCONTRAR TODOS LOS REGISTROS DE CLIENTES Y
    // CAMPAÑAS QUE SERAN MOSTRADOS EN EL REPORTE
    @Transactional()
    @Query(value = " SELECT clientes.id_cliente, clientes.nombre_cliente, clientes.tipo_cliente, cliente_aplica_campanha.id_campanha ,\n" +
            "                    productos.nombre_producto, campanhas.precio_campanha, campanhas.descripcion_campanha FROM cliente_aplica_campanha\n" +
            "                    INNER JOIN clientes ON cliente_aplica_campanha.id_cliente = clientes.id_cliente\n" +
            "                    INNER JOIN campanhas ON cliente_aplica_campanha.id_campanha = campanhas.id_campanha\n" +
            "                    INNER JOIN productos ON campanhas.id_producto = productos.id_producto\n" +
            "                    INNER JOIN tipo_producto ON productos.id_tipo = tipo_producto.id_tipo \n" +
            "                    WHERE  cliente_aplica_campanha.estado_cliente_campanha = 1   ",nativeQuery = true)
    List<clientesReporte> generarReporte();

    // QUERY NATIVO QUE SE ENCARGA DE CONSULTAR SI YA EXISTE UN REGISTRO EN LA TABLA DE CLIENTES Y CAMPAÑAS
    @Transactional
    @Query(value = "SELECT /*+ INDEX(cliente_aplica_campanha id_cliente)*/ count(id_cliente)  from cliente_aplica_campanha where id_cliente = :idCliente and " +
            "                id_campanha = :idCampanha AND (estado_cliente_campanha = 0 OR estado_cliente_campanha = 2)", nativeQuery = true)
    int consultarRegistroExistente(@Param("idCliente") Long idCliente, @Param("idCampanha") Long idCampanha);
}
