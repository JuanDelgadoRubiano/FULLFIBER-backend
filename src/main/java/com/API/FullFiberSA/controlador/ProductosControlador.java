package com.API.FullFiberSA.controlador;

import com.API.FullFiberSA.modelos.Productos;
import com.API.FullFiberSA.servicios.ProductosServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosControlador {
    // INYECCION DEL SERVICIO
    private final ProductosServicio productosServicio;
    public ProductosControlador(ProductosServicio productosServicio) {
        this.productosServicio = productosServicio;
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA REGISTRAR UN NUEVO PRODUCTO
    @PostMapping("/add")
    public ResponseEntity<Productos> adicionarProducto(@RequestBody Productos producto){
        Productos newProducto = productosServicio.adicionarProducto(producto);
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }

    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA OBTENER TODOS LOS PRODUCTOS DE UN DETERMINADO TIPO.
    @GetMapping("/find/{tipo}")
    public ResponseEntity<List<Productos>> obtenerProductosByTipo(@PathVariable("tipo") Long tipo){
        List<Productos> productos = productosServicio.encontrarProductosByIdTipo(tipo);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }


}
