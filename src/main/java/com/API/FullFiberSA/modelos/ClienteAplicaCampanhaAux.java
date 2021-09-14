package com.API.FullFiberSA.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteAplicaCampanhaAux {
    @Id
    private Long idCliente;
    private Long idCampanha;
    private Long estadoClienteCampanha;
}
