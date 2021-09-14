package com.API.FullFiberSA.servicios;

import com.API.FullFiberSA.modelos.LogsDeCambio;
import com.API.FullFiberSA.proyecciones.clientesReporte;
import com.API.FullFiberSA.proyecciones.logsReporte;
import com.API.FullFiberSA.repositorios.RepositorioLogsDeCambio;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class LogsDeCambioServicio {

    private final RepositorioLogsDeCambio repositorioLogsDeCambio;

    @Autowired
    public LogsDeCambioServicio(RepositorioLogsDeCambio repositorioLogsDeCambio){
        this.repositorioLogsDeCambio = repositorioLogsDeCambio;
    }

    // METODO PARA GENERAR UN LOG DE CAMBIO CUANDO SE LE MODIFIQUE LA CAMPAÑA A UN CLIENTE
    public LogsDeCambio adicionarLogDeCambio(LogsDeCambio log) {
        return repositorioLogsDeCambio.save(log);
    }

    //METODO QUE LLAMA A LA CONSULTA DE TODOS LOS LOGS DE CAMBIO PARA QUE SEAN MOSTRADOS EN EL REPORTE
    public List<logsReporte> generarReporte(){
        return repositorioLogsDeCambio.generarReporte();
    }

    //METODO PARA GENERAR EL ARCHIVO XLS QUE CONTIENE EL REPORTE DE LOGS DE CAMBIO.
    public ByteArrayInputStream exportarData() throws Exception {
        String[] columnas = {"Id Log de Cambio","Autor del cambio", "Fecha del Cambio", "Cliente receptor del cambio", "Campaña Anterior", "Campaña Nueva", "Razon del Cambio"};

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Reporte Logs de Cambio");
        Row row = sheet.createRow(0);

        for (int i= 0; i < columnas.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnas[i]);
        }

        List<logsReporte> logsReportes = this.generarReporte();
        int initRow = 1;

        for (logsReporte log : logsReportes) {
            System.out.println(log.getfecha_cambio_log());
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(log.getid_log_cambio());
            row.createCell(1).setCellValue(log.getnombre_admin());
            row.createCell(2).setCellValue(log.getfecha_cambio_log());
            row.createCell(3).setCellValue(log.getnombre_cliente());
            row.createCell(4).setCellValue(log.getid_campanha_anterior());
            row.createCell(5).setCellValue(log.getid_campanha_nueva());
            row.createCell(6).setCellValue(log.getcomentario_cambio());

            initRow++;

        }
        workbook.write(stream);
        return new ByteArrayInputStream(stream.toByteArray());
    }
}
