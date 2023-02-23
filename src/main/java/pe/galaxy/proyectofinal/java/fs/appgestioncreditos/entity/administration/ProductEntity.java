package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TBL_PRODUCTO")
@Entity(name = "ProductEntity")
public class ProductEntity {

    @Id
    @Column(name = "PRODUCTO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProducto")
    @SequenceGenerator(sequenceName = "SEQ_PRODUCTO", allocationSize = 1, name = "seqProducto")
    private Long idProduct;

    @ManyToOne
    @JoinColumn(name = "CATEGORIA_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_Categoria_Producto"))
    private CategoryEntity category;

    @NotNull
    @Size(min = 4, max = 4, message = "Este campo debe contener {max} caracteres")
    @Column(name = "CODIGO_PRODUCTO", unique = true)
    private String codeProduct;

    @NotNull
    @Size(min = 8, max = 120, message = "Este campo debe contener un minimo de {min} caracteres y un maximo de {max}")
    @Column(name = "NOMBRE_PRODUCTO")
    private String nameProduct;

    @NotNull
    @Positive(message = "Solo numeros positivos")
    @Column(name = "TASA_INTERES")
    private Double tax;

    @NotNull
    @Column(name = "ESTADO")
    private String status;

}
