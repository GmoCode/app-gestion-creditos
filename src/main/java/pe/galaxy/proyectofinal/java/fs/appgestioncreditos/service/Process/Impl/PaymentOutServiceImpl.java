package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Process.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.process.PaymentOutEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Process.IPaymentOutRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Process.IPaymentOutService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class PaymentOutServiceImpl implements IPaymentOutService {

    public IPaymentOutRepo repo;

    public PaymentOutServiceImpl(final IPaymentOutRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public Optional<PaymentOutEntity> findByIdAndStatus(Long id) throws ServiceException {

        try {
            return repo.findByIdAndStatus(id);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<PaymentOutEntity> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PaymentOutEntity> findAllStatus() throws ServiceException {
        return repo.findAllStatus();
    }

    @Override
    public List<PaymentOutEntity> findAll() throws ServiceException {
        return repo.findAll();
    }

    @Transactional
    @Override
    public PaymentOutEntity save(PaymentOutEntity paymentOutEntity) throws ServiceException {
        return repo.save(paymentOutEntity);
    }

    @Override
    public PaymentOutEntity update(PaymentOutEntity paymentOutEntity) throws ServiceException {

        try {
            PaymentOutEntity oPaymentOutEntity = this.findById(paymentOutEntity.getIdPaymentOut()).orElse(null);
            if(!Objects.isNull(oPaymentOutEntity)){
                BeanUtils.copyProperties(paymentOutEntity,oPaymentOutEntity);
                return repo.save(oPaymentOutEntity);
            }
        }catch (Exception e){
            throw new ServiceException(e);
        }
        return  null;
    }

    @Transactional
    @Override
    public Boolean delete(PaymentOutEntity paymentOutEntity) throws ServiceException {
        try {
            PaymentOutEntity oPaymentOutEntity = this.findById(paymentOutEntity.getIdPaymentOut()).orElse(null);
            if(!Objects.isNull(oPaymentOutEntity)){
                repo.delete(oPaymentOutEntity);
                return true;
            }
            return  null;
        }catch (Exception e){
            throw new ServiceException(e);
        }

    }
}
