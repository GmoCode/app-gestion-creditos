package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.repo.administration;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ProductEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.repo.Generic.IGenericRepo;

import java.util.List;
import java.util.Optional;

public interface IProductRepo extends IGenericRepo<ProductEntity, Long> {

    @Modifying
    @Query(nativeQuery=true, value= "UPDATE TBL_PRODUCTO SET estado='0' where producto_id=:id")
    void delete(@Param("id") Long id);

    @Query("select p from ProductEntity p where p.idProduct =:id and p.status = '1'")
    Optional<ProductEntity> findByIdAndStatus(Long id);

    @Query("select p from ProductEntity p where p.status = '1'")
    List<ProductEntity> findAllStatus();
}
