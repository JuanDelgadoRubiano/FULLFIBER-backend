package com.API.FullFiberSA.servicios;

import com.API.FullFiberSA.modelos.Campanhas;
import com.API.FullFiberSA.repositorios.RepositorioCampanha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CampanhaServicio {

    private final RepositorioCampanha repositorioCampanha;

    @Autowired
    public CampanhaServicio(RepositorioCampanha repositorioCampanha){
        this.repositorioCampanha = repositorioCampanha;
    }

    // METODO PARA REGISTRAR UNA NUEVA CAMPAÑA DE MERCADO
    public Campanhas adicionarCampanha(Campanhas campanhas) {
        return repositorioCampanha.save(campanhas);

    }


   // OBTENER LAS CAMPAÑAS QUE PERTENECEN A UN TIPO
   // (HAY QUE PAARAMETRIZAR QUE CUMPLA CON LOS REQUERIMIENTOS DEL CLIENTE ACTUAL AL QUE SE QUIERE MODIFICAR)
/*
    DE MOMENTO NO LO PUEDO HACER, NO PUEDO BUSCAR CAMPAÑAS SEGUN SU TIPO

    public List<Campanhas>  getCampanhasByTipo(Long tipo){
        return repositorioCampanha.getCampanhasByTipo(tipo);
    }

 */




}
