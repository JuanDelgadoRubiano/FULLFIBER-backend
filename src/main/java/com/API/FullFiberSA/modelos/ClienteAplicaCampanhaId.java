package com.API.FullFiberSA.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ClienteAplicaCampanhaId implements Serializable {

    @ManyToOne
    @JoinColumn(name ="idCampanha", referencedColumnName = "idcampanha", foreignKey = @ForeignKey(name = "FK_Id_Campanhas"))
    private Campanhas campanha;

    @ManyToOne
    @JoinColumn(name ="idCliente", referencedColumnName = "idCliente", foreignKey = @ForeignKey(name = "FK_Id_Clientes"))
    private Clientes cliente;
}
