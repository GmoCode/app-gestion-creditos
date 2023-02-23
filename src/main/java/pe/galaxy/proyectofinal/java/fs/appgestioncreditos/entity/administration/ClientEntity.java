package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TBL_CLIENTE")
@Entity(name = "ClientEntity")

public class ClientEntity {

    @Id
    @Column(name = "CLIENTE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
    @SequenceGenerator(sequenceName = "SEQ_CLIENTE", allocationSize = 1, name = "seqCliente")
    private Long idClient;

    @NotNull
    @Size(min = 2, max = 120, message = "Este campo debe contener un minimo de {min} caracteres y un maximo de {max}")
    @Column(name = "NOMBRES")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 120, message = "Este campo debe contener un minimo de {min} caracteres y un maximo de {max}")
    @Column(name = "APELLIDOS")
    private String lastName;

    @NotNull
    @Size(min = 8 ,max = 8, message = "Este campo debe contener {max} caracteres")
    @Column(name = "DNI")
    private String cardId;

    @NotNull
    @Size(min = 12 ,max = 12, message = "Agregar el codigo del pais, '+51' para Peru")
    @Column(name = "TELEFONO")
    private String phoneNumber;

    @NotNull
    @Email(message = "Ingrese un email valido")
    @Size(max = 120, message = "Este campo debe contener un maximo de {max} caracteres")
    @Column(name = "CORREO")
    private String emailAddress;


    @Column(name = "ESTADO")
    private String status = "1";


}
