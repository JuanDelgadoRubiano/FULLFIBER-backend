package com.API.FullFiberSA.repositorios;

import com.API.FullFiberSA.modelos.CondicionTipoAux;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RepositorioCampanhaCondicionTipo  {

    // DEFINICION DEL ENTITY MANAGER
    @PersistenceContext
    private EntityManager entityManager;

    // INSERCION DE UNA CONDICION POR TIPO PARA UNA CAMPAÑA HACIENDO USO DE JPQL
    @Transactional
    public void adicionarCampanhaCondicionTipo(CondicionTipoAux CondicionTipoAux) {
        entityManager.createNativeQuery("INSERT INTO campanha_condicion_tipo (condicion_tipo, id_campanha) VALUES (?,?)")
                .setParameter(1, CondicionTipoAux.getCondicion())
                .setParameter(2, CondicionTipoAux.getIdCampanha())
                .executeUpdate();
    }


}
