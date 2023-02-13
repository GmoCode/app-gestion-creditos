package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserName(String usuario);
		
}
