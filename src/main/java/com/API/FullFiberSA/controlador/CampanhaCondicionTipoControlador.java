package com.API.FullFiberSA.controlador;

import com.API.FullFiberSA.modelos.CondicionTipoAux;
import com.API.FullFiberSA.servicios.CampanhaCondicionTipoServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/condicionTipo")
public class CampanhaCondicionTipoControlador {

    // INYECCION DEL SERVICIO
    private final CampanhaCondicionTipoServicio campanhaCondicionTipoServicio;
    public CampanhaCondicionTipoControlador(CampanhaCondicionTipoServicio campanhaCondicionTipoServicio){
        this.campanhaCondicionTipoServicio = campanhaCondicionTipoServicio;
    }
    //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA ADICIONAR UNA CONDICION POR TIPO A UNA CAMPAÑA
    @PostMapping("/add")
    public void adicionarCampanhaCondicionTipo(@RequestBody CondicionTipoAux campanhaCondicionTipo){
        campanhaCondicionTipoServicio.adicionarCampanhaCondicionTipo(campanhaCondicionTipo);
    }

}
