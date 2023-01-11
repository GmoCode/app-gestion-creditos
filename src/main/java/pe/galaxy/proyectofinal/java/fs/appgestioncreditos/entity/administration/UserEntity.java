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
@Table(name = "TBL_USUARIO")
@Entity (name = "UserEntity")

public class UserEntity {

    @Id
    @Column(name = "USUARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    @SequenceGenerator(sequenceName = "SEQ_USER", allocationSize = 1, name = "seqUser")
    private Long idUser;

    @NotNull
    @Size(min = 4, max = 20, message = "Este campo debe contener entre {min} y {max} caracteres")
    @Column(name = "NOMBRE_USUARIO", unique = true)
    private String userName;

    @NotNull
    @Size(min = 8, max = 120, message = "Este campo debe contener como minimo {min} caracteres")
    @Column(name = "CONTRASENA")
    private String password;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false,foreignKey = @ForeignKey(name = "FK_Usuario_Role"))
    private RoleEntity role;

    @NotNull
    @Column(name = "ESTADO")
    private String status;
}
