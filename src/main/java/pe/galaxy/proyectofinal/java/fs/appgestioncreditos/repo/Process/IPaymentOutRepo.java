package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Process;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.process.PaymentOutEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Generic.IGenericRepo;

import java.util.List;
import java.util.Optional;

public interface IPaymentOutRepo extends IGenericRepo<PaymentOutEntity, Long> {

    @Modifying
    @Query(nativeQuery=true, value= "UPDATE TBL_DESEMBOLSO SET estado='0' where desembolso_id=:id")
    void delete(@Param("id") Long id);

    @Query("select p from PaymentOutEntity p where p.idPaymentOut =:id and p.status = '1'")
    Optional<PaymentOutEntity> findByIdAndStatus(Long id);

    @Query("select p from PaymentOutEntity p where p.status = '1'")
    List<PaymentOutEntity> findAllStatus();



}
