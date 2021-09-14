package com.API.FullFiberSA.controlador;

import com.API.FullFiberSA.modelos.LogsDeCambio;
import com.API.FullFiberSA.proyecciones.logsReporte;
import com.API.FullFiberSA.servicios.LogsDeCambioServicio;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/LogsDeCambio")
public class LogsDeCambioControlador {

    //INYECCION DEL SERVICIO
    public final LogsDeCambioServicio logsDeCambioServicio;
    public LogsDeCambioControlador(LogsDeCambioServicio logsDeCambioServicio){
        this.logsDeCambioServicio = logsDeCambioServicio;
    }

    //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA CREAR UN LOG DE CAMBIO CUANDO SE MODIFICA
    // MANUALMENTE LA CAMPAÑA A LA QUE APLICA UN CLIENTE
    @PostMapping("/add")
    public ResponseEntity<LogsDeCambio> adicionarLogDeCambio(@RequestBody LogsDeCambio log){
        LogsDeCambio newLog = logsDeCambioServicio.adicionarLogDeCambio(log);
        return new ResponseEntity<>(newLog, HttpStatus.CREATED);
    }
    // CONTROLADOR  QUE LLAMA AL METODO EN EL SERVICIO PARA ENCONTRAR TODOS LOS LOGS DE CAMBIO REGISTRADOS
    // EN EL SISTEMA PARA POSTERIORMENTE SER PUESTOS EN UN ARCHIVO EXCELL
    @GetMapping("/reporte")
    public ResponseEntity<List<logsReporte>> generarReporte(){
        List<logsReporte> newLog = logsDeCambioServicio.generarReporte();
        return new ResponseEntity<>(newLog, HttpStatus.CREATED);
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA GENERAR UN ARCHIVO EXCELL CON TODOS
    // LOS LOGS DE CAMBIO REGISTRADOS  EN EL SISTEMA
    @GetMapping("/generarReporte")
    public ResponseEntity<InputStreamResource> generarReporte2() throws  Exception{
        ByteArrayInputStream stream = logsDeCambioServicio.exportarData();
        HttpHeaders headers  = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ReporteLogsDeCambio.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }
}
