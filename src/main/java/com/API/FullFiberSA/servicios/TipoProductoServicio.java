package com.API.FullFiberSA.servicios;

import com.API.FullFiberSA.modelos.TipoProducto;
import com.API.FullFiberSA.repositorios.RepositorioTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoServicio {

    private final RepositorioTipo repositorioTipo;

    @Autowired
    public TipoProductoServicio(RepositorioTipo repositorioTipo){
        this.repositorioTipo = repositorioTipo;
    }

    // METODO PARA INSERTAR UN NUEVO REGISTRO DE TIPO DE PRODUCTOS
    public TipoProducto adicionarTipoProducto(TipoProducto tipo){
        return repositorioTipo.save(tipo);
    }

    // METODO QUE OBTIENE TODOS LOS TIPOS DE PRODUCTO
    public List<TipoProducto> obtenerTiposProducto() {
        return repositorioTipo.findAll();
    }
}
