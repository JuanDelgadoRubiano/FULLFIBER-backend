package com.API.FullFiberSA.repositorios;

import com.API.FullFiberSA.modelos.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioTipo extends JpaRepository<TipoProducto,Long> {
}
