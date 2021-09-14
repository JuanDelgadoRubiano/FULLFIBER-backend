package com.API.FullFiberSA.servicios;

import com.API.FullFiberSA.modelos.Productos;
import com.API.FullFiberSA.repositorios.RepositorioProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServicio {

    private final RepositorioProductos repoProductos;

    @Autowired
    public ProductosServicio(RepositorioProductos repoProductos) {
        this.repoProductos = repoProductos;
    }

    // METODO PARA CREAR UN NUEVO REGISTRO DE UN PRODUCTO
    public Productos adicionarProducto(Productos producto) {
        return repoProductos.save(producto);

    }

    // METODO QUE OBTIENE TODOS LOS PRODUCTOS SEGUN SU TIPO
    public List<Productos> encontrarProductosByIdTipo(Long tipo){
        return repoProductos.encontrarProductosByIdTipo(tipo);
    }


}
