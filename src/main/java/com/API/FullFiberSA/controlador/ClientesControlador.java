package com.API.FullFiberSA.controlador;

import com.API.FullFiberSA.modelos.Clientes;
import com.API.FullFiberSA.servicios.ClienteServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
public class ClientesControlador {
    // INYECCION DEL SERVICIO
    private final ClienteServicio clienteServicio;
    public ClientesControlador(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA CREAR UN NUEVO CLIENTE
    @PostMapping("/add")
    public ResponseEntity<Clientes> adicionarCliente(@RequestBody Clientes cliente){
        Clientes newCliente = clienteServicio.adicionarCliente(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

   //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA ENCONTRAR UN CLIENTE A PARTIR DE SU DOCUMENTO
    @GetMapping("/find/{documento}")
    public ResponseEntity<Clientes> encontrarClienteByDocumento(@PathVariable("documento")int documento) {
        Clientes cliente = clienteServicio.encontrarClienteByDocumento(documento);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }


}
