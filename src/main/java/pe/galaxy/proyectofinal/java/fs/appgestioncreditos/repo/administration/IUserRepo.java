package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.UserEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Generic.IGenericRepo;

import java.util.List;
import java.util.Optional;

public interface IUserRepo extends IGenericRepo<UserEntity,Long > {

    @Modifying
    @Query(nativeQuery=true, value= "UPDATE TBL_USUARIO SET estado='0' where usuario_id=:id")
    void delete(@Param("id") Long id);

    @Query("select p from UserEntity p where p.idUser =:id and p.status = '1'")
    Optional<UserEntity> findByIdAndStatus(Long id);

    @Query("select p from UserEntity p where p.status = '1'")
    List<UserEntity> findAllStatus();
}
