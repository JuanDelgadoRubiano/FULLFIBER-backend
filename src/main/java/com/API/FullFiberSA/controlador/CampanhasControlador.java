package com.API.FullFiberSA.controlador;

import com.API.FullFiberSA.modelos.Campanhas;
import com.API.FullFiberSA.servicios.CampanhaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campanhas")
public class CampanhasControlador {

    // INYECCION DEL SERVICIO
    private final CampanhaServicio campanhaServicio;
    public CampanhasControlador(CampanhaServicio campanhaServicio){
        this.campanhaServicio = campanhaServicio;
    }

    //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA CREAR UNA NUEVA CAMPAÑA DE MERCADO
    @PostMapping("/add")
    public ResponseEntity<Campanhas> adicionarCampanha(@RequestBody Campanhas campanha){
        Campanhas newCampanha = campanhaServicio.adicionarCampanha(campanha);
        return new ResponseEntity<>(newCampanha, HttpStatus.CREATED);
    }

}
