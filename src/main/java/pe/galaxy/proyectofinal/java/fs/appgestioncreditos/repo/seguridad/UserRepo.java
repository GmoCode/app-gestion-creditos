package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserName(String usuario);
	@Modifying
	@Query(nativeQuery=true, value= "UPDATE TBL_USUARIO SET estado='0' where id_usuario=:id")
	void delete(@Param("id") Long id);

	@Query("select p from UserEntity p where p.idUser =:id and p.status = '1'")
	Optional<UserEntity> findByIdAndStatus(Long id);

	@Query("select p from UserEntity p where p.status = '1'")
	List<UserEntity> findAllStatus();
}
