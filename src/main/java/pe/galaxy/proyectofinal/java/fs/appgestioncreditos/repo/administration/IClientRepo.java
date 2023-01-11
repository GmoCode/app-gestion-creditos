package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ClientEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Generic.IGenericRepo;

import java.util.List;
import java.util.Optional;

public interface IClientRepo extends IGenericRepo<ClientEntity, Long> {

    @Modifying
    @Query(nativeQuery=true, value= "UPDATE TBL_CLIENTE SET estado='0' where cliente_id=:id")
    void delete(@Param("id") Long id);

    @Query("select p from ClientEntity p where p.idClient =:id and p.status = '1'")
    Optional<ClientEntity> findByIdAndStatus(Long id);

    @Query("select p from ClientEntity p where p.status = '1'")
    List<ClientEntity> findAllStatus();

}
