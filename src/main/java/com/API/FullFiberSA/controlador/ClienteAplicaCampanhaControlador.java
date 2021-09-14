package com.API.FullFiberSA.controlador;

import com.API.FullFiberSA.modelos.ClienteAplicaCampanhaAux;
import com.API.FullFiberSA.proyecciones.clientesCampanha;
import com.API.FullFiberSA.servicios.ClienteAplicaCampanhaServicio;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/clienteCampanha")
public class ClienteAplicaCampanhaControlador {

    // INYECCION DEL SERVICIO
    private final ClienteAplicaCampanhaServicio clienteAplicaCampanhaServicio;
    public ClienteAplicaCampanhaControlador(ClienteAplicaCampanhaServicio clienteAplicaCampanhaServicio){
        this.clienteAplicaCampanhaServicio = clienteAplicaCampanhaServicio;
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA GENERAR UN NUEVO
    // REGISTRO DE UN CLIENTE Y LA CAMPAÑA A LA QUE APLICA
    @PostMapping("/add")
    public void adicionarClienteCampanha(@RequestBody ClienteAplicaCampanhaAux clienteCampanha){
        clienteAplicaCampanhaServicio.insertarClienteAplicaCampanha(clienteCampanha);
    }
    //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA EDITAR UN
    // REGISTRO DE CLIENTE Y LA CAMPAÑA A LA QUE APLICA
    @PutMapping("/editar")
    public void modificarClienteCampanha(@RequestBody ClienteAplicaCampanhaAux clienteCampanha){
         clienteAplicaCampanhaServicio.actualizarClienteCampanha(clienteCampanha);
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA ENCONTRAR
    // LAS CAMPAÑAS A LAS QUE APLICA UN CLIENTE SEGUN EL ID DEL CLIENTE
    @GetMapping("/find/{idCliente}")
    public ResponseEntity<List<clientesCampanha>> encontrarCampanhaByIdCliente(@PathVariable("idCliente")Long idCliente) {
        List<clientesCampanha> campanhasCliente = clienteAplicaCampanhaServicio.encontrarCampanhaByIdCliente(idCliente);
        return new ResponseEntity<>(campanhasCliente, HttpStatus.OK);
    }

    //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA HACER EL LLAMADO AL PROCESAMIENTO MASIVO
    // Y SE ASIGNEN A LOS CLIENTES LAS CAMPAÑAS CORRESPONDIENTES
    @GetMapping("/asignarCampanha")
    public int asignarCampanhas() throws SQLException, ClassNotFoundException {
        return clienteAplicaCampanhaServicio.verificarAplicabilidadClientes();
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA ENCONTRAR TODAS LAS CAMPAÑAS
    // A LAS QUE PUEDE LLEGAR A APLICAR UN CLIENTE PARA POSTERIORMENTE MODIFICAR LA ACTUAL
    @GetMapping("/findOtros/{idCliente}/{tipo}")
    public ResponseEntity<List<clientesCampanha>> encontrarOtrasCampanhasByIdCliente(@PathVariable("idCliente")Long idCliente, @PathVariable("tipo") int tipo) {
        List<clientesCampanha> campanhasCliente = clienteAplicaCampanhaServicio.encontrarOtrasCampanhasByIdCliente(idCliente, tipo);
        return new ResponseEntity<>(campanhasCliente, HttpStatus.OK);
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO QUE TRAE TODOS LOS CLIENTES Y LAS CAMPAÑAS
    // A LAS QUE APLICAN PARA GENERAR UNA HOJA DE CALCULO CON DICHA INFORMACION
    @GetMapping("/generarReporte")
    public ResponseEntity<InputStreamResource> generarReporte() throws  Exception{
        ByteArrayInputStream stream = clienteAplicaCampanhaServicio.exportarData();
        HttpHeaders headers  = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ReporteClientesCampaña.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA CONSULTAR SI YA EXISTE UN
    // REGISTRO EN LA TABLA DE CLIENTES Y CAMPAÑAS
    @GetMapping("/consultar/{idCliente}/{idCampanha}")
    public int consultarRegistroExistente(@PathVariable("idCliente") Long idCliente, @PathVariable("idCampanha") Long idCampanha){
        return clienteAplicaCampanhaServicio.consultarRegistroExistente(idCliente, idCampanha);
    }


}
