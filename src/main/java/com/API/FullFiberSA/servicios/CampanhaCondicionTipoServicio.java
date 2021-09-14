package com.API.FullFiberSA.servicios;


import com.API.FullFiberSA.modelos.CondicionTipoAux;
import com.API.FullFiberSA.repositorios.RepositorioCampanhaCondicionTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampanhaCondicionTipoServicio {

    private final RepositorioCampanhaCondicionTipo repositorioCampanhaCondicionTipo;

    @Autowired
    public CampanhaCondicionTipoServicio(RepositorioCampanhaCondicionTipo repositorioCampanhaCondicionTipo){
        this.repositorioCampanhaCondicionTipo = repositorioCampanhaCondicionTipo;
    }
    // METODO PARA ADICIONAR UNA NUEVA CONDICION POR TIPO A UNA CAMPAÑA
    public void adicionarCampanhaCondicionTipo(CondicionTipoAux campanhaCondicionTipo){
        repositorioCampanhaCondicionTipo.adicionarCampanhaCondicionTipo(campanhaCondicionTipo);
    }



}
