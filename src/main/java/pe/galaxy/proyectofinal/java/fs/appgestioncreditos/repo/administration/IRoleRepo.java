package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.RoleEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Generic.IGenericRepo;

import java.util.List;
import java.util.Optional;

public interface IRoleRepo extends IGenericRepo<RoleEntity, Long> {

    @Modifying
    @Query(nativeQuery=true, value= "UPDATE TBL_ROL SET estado='0' where role_id=:id")
    void delete(@Param("id") Long id);

    @Query("select p from RoleEntity p where p.idRole =:id and p.status = '1'")
    Optional<RoleEntity> findByIdAndStatus(Long id);

    @Query("select p from RoleEntity p where p.status = '1'")
    List<RoleEntity> findAllStatus();
}
