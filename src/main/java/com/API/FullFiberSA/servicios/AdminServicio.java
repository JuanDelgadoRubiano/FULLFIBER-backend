package com.API.FullFiberSA.servicios;

import com.API.FullFiberSA.modelos.Administradores;
import com.API.FullFiberSA.repositorios.RepositorioAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicio {

    private final RepositorioAdmin repositorioAdmin;

    @Autowired
    public AdminServicio(RepositorioAdmin repositorioAdmin){
        this.repositorioAdmin = repositorioAdmin;
    }

    // METODO PARA CREAR UN NUEVO ADMINISTRADOR
    public Administradores adicionarAdmin(Administradores administradores) {
        return repositorioAdmin.save(administradores);

    }

    //METODO PARA OBTENER UN ADMINISTRADOR A PARTIR DE SU NOMBRE
    public Administradores encontrarAdmin(String nombre){
        return repositorioAdmin.encontrarAdminByNombre(nombre);
    }
}
