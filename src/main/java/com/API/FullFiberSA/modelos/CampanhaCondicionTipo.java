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
public class CampanhaCondicionTipo implements Serializable {

    @EmbeddedId
    private CampanhaCondicionTipoId idCampanhaCondicionTipo;
}
