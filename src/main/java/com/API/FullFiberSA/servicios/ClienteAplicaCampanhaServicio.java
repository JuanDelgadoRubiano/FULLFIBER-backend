package com.API.FullFiberSA.servicios;

import com.API.FullFiberSA.modelos.*;
import com.API.FullFiberSA.proyecciones.clientesCampanha;
import com.API.FullFiberSA.proyecciones.clientesReporte;
import com.API.FullFiberSA.repositorios.RepositorioClienteAplicaCampanha;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.List;


@Service
public class ClienteAplicaCampanhaServicio {

    private final RepositorioClienteAplicaCampanha repositorioClienteAplicaCampanha;

    @Autowired
    public ClienteAplicaCampanhaServicio(RepositorioClienteAplicaCampanha repositorioClienteAplicaCampanha){
        this.repositorioClienteAplicaCampanha = repositorioClienteAplicaCampanha;
    }

    // DEFINICION DEL ENTITY MANAGER
    @PersistenceContext
    private EntityManager entityManager;

    // METODO QUE HACE EL LLAMADO AL PROCESAMIENTO MASIVO Y VERIFICA LOS CLIENTES Y
    // LAS CAMPAÑAS A LAS QUE APLICAN
    public int verificarAplicabilidadClientes() throws SQLException, ClassNotFoundException {
        return RepositorioClienteAplicaCampanha.verificarAplicabilidadClientes();
    }

    // METODO QUE HACE EL LLAMADO A LA BUSQUEDA DE CAMPAÑAS A LAS QUE APLICA UN CLIENTE A PARTIR DE SU ID
    public List<clientesCampanha> encontrarCampanhaByIdCliente(Long idCliente){
        return repositorioClienteAplicaCampanha.encontrarCampanhaByIdCliente(idCliente);
    }

    // METODO QUE HACE EL LLAMADO A LA BUSQUEDA DE TODAS LAS CAMPAÑAS A LAS QUE PUEDE APLICAR UN
    // CLIENTE  SEGUN EL TIPO DE CAMPAÑA Y EL ID DEL CLIENTE PARA SU POSIBLE MODIFICACION
    public List<clientesCampanha> encontrarOtrasCampanhasByIdCliente(Long idCliente, int tipo){
        List<clientesCampanha> respuesta = null;
        if(tipo == 1) {
            respuesta = repositorioClienteAplicaCampanha.encontrarOtrasCampanhasTvByIdCliente(idCliente);
        }
        else if(tipo == 2) {
            respuesta = repositorioClienteAplicaCampanha.encontrarOtrasCampanhasTelByIdCliente(idCliente);
        }
        else if(tipo == 3){
            respuesta = repositorioClienteAplicaCampanha.encontrarOtrasCampanhasInterByIdCliente(idCliente);
        }
        return respuesta;
    }

    // METODO QUE HACE EL LLAMADO A LA BUSQUEDA DE TODOS LOS REGISTROS DE CLIENTES Y
    // CAMPAÑAS PARA GENERAR EL REPORTE
    public List<clientesReporte> generarReporte(){
        return repositorioClienteAplicaCampanha.generarReporte();
    }

    // METODO QUE GENERA EL ARCHIVO XLS QUE CONTIENE EL REPORTE DE CLIENTES Y CAMPAÑAS
   public ByteArrayInputStream exportarData() throws Exception {
        String[] columnas = {"Id-Cliente","Nombre-Cliente", "Tipo-Cliente", "Id-Campaña", "Nombre-Producto-en-Campaña", "Precio-Campaña ", "Descripcion-De-Campaña"};

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Reporte Clientes y Campañas");
        Row row = sheet.createRow(0);

        for (int i= 0; i < columnas.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnas[i]);
        }

        List<clientesReporte> clientesCampanha = this.generarReporte();
        int initRow = 1;

        for (clientesReporte cliente : clientesCampanha) {
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(cliente.getid_cliente());
            row.createCell(1).setCellValue(cliente.getnombre_cliente());
            row.createCell(2).setCellValue(cliente.gettipo_cliente());
            row.createCell(3).setCellValue(cliente.getid_campanha());
            row.createCell(4).setCellValue(cliente.getnombre_producto());
            row.createCell(5).setCellValue(cliente.getprecio_campanha());
            row.createCell(6).setCellValue(cliente.getdescripcion_campanha());

            initRow++;

        }
        workbook.write(stream);
        return new ByteArrayInputStream(stream.toByteArray());
    }

    // METODO PARA INSERTAR UN NUEVO REGISTRO DE CLIENTES Y CAMPAÑAS
    @Transactional
    public void insertarClienteAplicaCampanha(ClienteAplicaCampanhaAux clienteAplicaCampanha) {
        entityManager.createNativeQuery("INSERT INTO cliente_aplica_campanha (estado_cliente_campanha, id_cliente, id_campanha) VALUES (?,?,?)")
                .setParameter(1, clienteAplicaCampanha.getEstadoClienteCampanha())
                .setParameter(2, clienteAplicaCampanha.getIdCliente())
                .setParameter(3,clienteAplicaCampanha.getIdCampanha())
                .executeUpdate();
    }

    // METODO PARA MODIFICAR UN REGISTRO DE CLIENTES Y CAMPAÑAS
    @Transactional
    public void actualizarClienteCampanha(ClienteAplicaCampanhaAux clienteAplicaCampanha) {
        entityManager.createNativeQuery("UPDATE  cliente_aplica_campanha SET estado_cliente_campanha = ? WHERE id_campanha = ? AND id_cliente = ?")
                .setParameter(1,clienteAplicaCampanha.getEstadoClienteCampanha())
                .setParameter(2, clienteAplicaCampanha.getIdCampanha())
                .setParameter(3, clienteAplicaCampanha.getIdCliente())
                .executeUpdate();
    }

    // METODO QUE HACE EL LLAMADO A LA CONSULTA DE LA EXISTENCIA DE UN REGISTRO
    public int consultarRegistroExistente(Long idCliente, Long idCampanha){
        return repositorioClienteAplicaCampanha.consultarRegistroExistente(idCliente, idCampanha);
    }

}
