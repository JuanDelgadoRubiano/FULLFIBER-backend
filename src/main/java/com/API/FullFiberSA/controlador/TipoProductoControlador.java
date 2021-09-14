package com.API.FullFiberSA.controlador;


import com.API.FullFiberSA.modelos.TipoProducto;
import com.API.FullFiberSA.servicios.TipoProductoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo")
public class TipoProductoControlador {

    // INYECCION DEL SERVICIO
    private final TipoProductoServicio tipoProductoServicio;
    public TipoProductoControlador(TipoProductoServicio tipoProductoServicio){
        this.tipoProductoServicio = tipoProductoServicio;
    }
    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA OBTENER TODOS LOS TIPOS DE PRODUCTOS
    @GetMapping("/all")
    public ResponseEntity<List<TipoProducto>> obtenerTiposProducto () {
        List<TipoProducto> tipos = tipoProductoServicio.obtenerTiposProducto();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA ADICIONAR UN NUEVO TIPO DE PRODUCTO
    @PostMapping("/add")
    public ResponseEntity<TipoProducto> adicionarTipoProducto(@RequestBody TipoProducto tipo){
        TipoProducto newTipo = tipoProductoServicio.adicionarTipoProducto(tipo);
        return new ResponseEntity<>(newTipo, HttpStatus.CREATED);
    }
}
