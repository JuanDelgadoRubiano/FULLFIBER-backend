package com.API.FullFiberSA.controlador;

import com.API.FullFiberSA.modelos.Administradores;
import com.API.FullFiberSA.servicios.AdminServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminControlador {

    // INYECCION DEL SERVICIO
    private final AdminServicio adminServicio;
    public AdminControlador(AdminServicio adminServicio){
        this.adminServicio = adminServicio;
    }

    //CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA CREAR UN NUEVO USUARIO ADMINISTRADOR
    @PostMapping("/add")
    public ResponseEntity<Administradores> adicionarAdmin(@RequestBody Administradores administradores){
        Administradores newAdministradores = adminServicio.adicionarAdmin(administradores);;
        return new ResponseEntity<>(newAdministradores, HttpStatus.CREATED);
    }
    // CONTROLADOR QUE LLAMA AL METODO EN EL SERVICIO PARA ENCONTRAR UN ADMINISTRADOR A PARTIR DE SU NOMBRE
    @GetMapping("/find/{nombre}")
    public ResponseEntity<Administradores> encontrarAdmin(@PathVariable("nombre") String nombre){
        Administradores newAdmin = adminServicio.encontrarAdmin(nombre);
        return new ResponseEntity<>(newAdmin, HttpStatus.OK);
    }
}
