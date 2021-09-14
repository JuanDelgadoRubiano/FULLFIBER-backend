package com.API.FullFiberSA.servicios;

import com.API.FullFiberSA.modelos.Clientes;
import com.API.FullFiberSA.repositorios.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    private final RepositorioCliente repositorioCliente;

    @Autowired
    public ClienteServicio(RepositorioCliente repositorioCliente){
        this.repositorioCliente = repositorioCliente;
    }
    // METODO PARA CREAR UN NUEVO REGISTRO DE UN CLIENTE
    public Clientes adicionarCliente(Clientes cliente){
        return repositorioCliente.save(cliente);
    }

    // METODO QUE HACE EL LLAMADO A ENCONTRAR UN CLIENTE A PARTIR DE SU DOCUMENTO DE IDENTIDAD
    public Clientes encontrarClienteByDocumento(int documento){
        return repositorioCliente.encontrarClienteByDocumento(documento);
    }




}
