package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.repo.administration;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.CategoryEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.repo.Generic.IGenericRepo;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepo extends IGenericRepo<CategoryEntity, Long> {

    @Modifying
    @Query(nativeQuery=true, value= "UPDATE TBL_CATEGORIA SET estado='0' where categoria_id=:id")
    void delete(@Param("id") Long id);

    @Query("select p from CategoryEntity p where p.idCategory =:id and p.status = '1'")
    Optional<CategoryEntity> findByIdAndStatus(Long id);

    @Query("select p from CategoryEntity p where p.status = '1'")
    List<CategoryEntity> findAllStatus();
}
