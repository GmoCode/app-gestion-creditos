package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_AUTHORITY")
@ToString
@Data
public class AuthorityEntity{

	@Id
	@Column(name = "AUTHORITY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAuthority")
	@SequenceGenerator(sequenceName = "SEQ_AUTHORITY", allocationSize = 1, name = "seqAuthority")
	private Long id = 0L;

	@Column(name = "NOMBRE")
	private String nameAuthority = "";

}
