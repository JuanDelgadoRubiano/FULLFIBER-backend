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
@Cacheable(value = false)
@Table(
        name = "Clientes",
        indexes = {
                @Index(name = "IDX_Clientes_Documento", columnList = "documentoCliente")
        }
)
public class Clientes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false,updatable = false)
    private Long idCliente;
    private String nombreCliente;
    @Column(unique = true)
    private int documentoCliente;
    private String tipoCliente;
    private int promFacturacionMensualCliente;
    private int promTvCliente;
    private int promInterCliente;
    private int promTelCliente;
    private int calificacionCliente;
}
