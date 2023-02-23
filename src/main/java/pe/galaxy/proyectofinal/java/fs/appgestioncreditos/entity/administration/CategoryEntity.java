package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TBL_CATEGORIA")
@Entity(name = "CategoryEntity")

public class CategoryEntity {

    @Id
    @Column(name = "CATEGORIA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCategoria")
    @SequenceGenerator(sequenceName = "SEQ_CATEGORIA", allocationSize = 1, name = "seqCategoria")
    private Long idCategory;

    @NotNull
    @Size(min = 2, max = 120, message = "Este campo debe contener un minimo de {min} caracteres y un maximo de {max}")
    @Column(name = "NOMBRE_CATEGORIA")
    private String categoryName;
    @Size(min = 2, max = 240, message = "Este campo debe contener un minimo de {min} caracteres y un maximo de {max}")
    @Column(name = "DESCRIPCION_CATEGORIA")
    private String categoryDescription;

    @Column(name = "ESTADO")
    private String status = "1";

}
