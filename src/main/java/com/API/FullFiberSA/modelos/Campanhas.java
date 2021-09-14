package com.API.FullFiberSA.modelos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(false)
@Table(
        name = "Campanhas",
        indexes = {
                @Index(name = "IDX_Campanhas_Calificacion", columnList = "condicionCalificacion"),
                @Index(name = "IDX_Campanhas_CondicionFacturaGeneralInicio", columnList =  "condicionFacturaGeneralInicio")
        }
)
public class Campanhas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false,updatable = false)
    private Long idCampanha;
    private int precioCampanha;
    //RECUERDA QUE CAMBIASTE EL TIPO DE LAS FECHAS :v 
    private Date fechaInicioCampanha;
    private Date fechaFinalCampanha;
    private int condicionCalificacion;
    private int condicionFacturaGeneralInicio;
    private int condicionFacturaGeneralFinal;
    private int condicionFacturaInicio;
    private int condicionFacturaFinal;
    private String descripcionCampanha;
    private int estadoCampanha;

    @ManyToOne
    @JoinColumn(name ="idAdmin",nullable = false,updatable = false, referencedColumnName = "idAdmin", foreignKey = @ForeignKey(name = "FK_Id_AdminCreador"))
    private Administradores administradores;

    @ManyToOne
    @JoinColumn(name= "idProducto", nullable = false, updatable = false, referencedColumnName = "idProducto", foreignKey = @ForeignKey(name = "FK_Id_Producto"))
    private Productos producto;
}
