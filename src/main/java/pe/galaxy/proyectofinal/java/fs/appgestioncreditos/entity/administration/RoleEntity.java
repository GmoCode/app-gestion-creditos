package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TBL_ROL")
@Entity(name = "RoleEntity")

public class RoleEntity {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRol")
    @SequenceGenerator(sequenceName = "SEQ_ROL", allocationSize = 1, name = "seqRol")
    private Long idRole;

    @Size(min = 4, max = 20, message = "Este campo debe contener entre {min} y {max} caracteres")
    @Column(name = "NOMBRE_ROL")
    private String nameRole;

    @NotNull
    @Column(name = "ESTADO")
    private String status;

}
