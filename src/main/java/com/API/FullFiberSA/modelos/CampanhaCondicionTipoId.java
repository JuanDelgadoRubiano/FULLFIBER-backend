package com.API.FullFiberSA.modelos;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CampanhaCondicionTipoId implements Serializable {

    @ManyToOne
    @JoinColumn (name ="idCampanha", referencedColumnName = "idcampanha", foreignKey = @ForeignKey(name = "FK_Id_Campanha"))
    private Campanhas campanha;

    private String condicionTipo;
}
