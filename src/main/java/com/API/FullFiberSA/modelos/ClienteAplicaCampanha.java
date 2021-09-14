package com.API.FullFiberSA.modelos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(false)
@Table(
        name = "ClienteAplicaCampanha",
        indexes = {
                @Index(name = "IDX_CampanhasCliente_idCliente", columnList = "idCliente")
        }
)
public class ClienteAplicaCampanha implements Serializable {

// MODIFICAR ID COMO DIJO EL PASCUYO!!!!!

    @EmbeddedId
    private ClienteAplicaCampanhaId clienteAplicaCampanhaId;
    private int estadoClienteCampanha;

}
