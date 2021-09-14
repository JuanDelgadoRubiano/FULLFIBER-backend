package com.API.FullFiberSA.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class LogsDeCambio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false,updatable = false)
    private Long idLogCambio;

    @ManyToOne
    @JoinColumn(name ="idAdmin", nullable = false, referencedColumnName = "idAdmin", foreignKey = @ForeignKey(name = "FK_Id_Admin"))
    private Administradores administrador;

    @ManyToOne
    @JoinColumn(name ="idCliente",nullable = false, referencedColumnName = "idCliente", foreignKey = @ForeignKey(name = "FK_Id_Cliente"))
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name ="idCampanhaAnterior",nullable = false, referencedColumnName = "idcampanha",  updatable = false ,foreignKey = @ForeignKey(name = "FK_Id_Campanha1"))
    private Campanhas campanhaAnterior;

    @ManyToOne
    @JoinColumn(name ="idCampanhaNueva",nullable = false, referencedColumnName = "idcampanha",  updatable = false, foreignKey = @ForeignKey(name = "FK_Id_Campanha2"))
    private Campanhas campanhaNueva;

    private String comentarioCambio;

    @JsonFormat(pattern = "dd/mm/yyyy")
    @Column(nullable = false,updatable = false)
    private Date fechaCambioLog;
    private Date newDate(){
        return new Date();
    }
    @PrePersist
    void createdAt() {
        this.fechaCambioLog = newDate();
    }

}
