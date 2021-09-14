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
    name = "Productos",
    indexes = {
            @Index(name = "IDX_Productos_TipoProducto", columnList = "idTipo")
        }
)
public class Productos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false,updatable = false)
    private long   idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    @ManyToOne
    @JoinColumn(name ="idTipo",nullable = false,updatable = false, referencedColumnName = "idTipo", foreignKey = @ForeignKey(name = "FK_Id_TipoProducto"))
    private TipoProducto tipoProducto;
}
