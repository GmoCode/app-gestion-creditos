package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Process.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.CategoryEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ProductEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.process.PaymentOutDetailEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.process.PaymentOutEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Process.IPaymentOutRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration.IProductRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Process.IPaymentOutService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.exception.ServiceException;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util.BDUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaymentOutServiceImpl implements IPaymentOutService {

    public IPaymentOutRepo repo;
    public IProductRepo productRepo;

    public PaymentOutServiceImpl(final IPaymentOutRepo repo, final IProductRepo productRepo) {
        super();
        this.repo = repo;
        this.productRepo = productRepo;
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
    public PaymentOutEntity save(PaymentOutEntity paymentOut) throws ServiceException {

        try{

            Optional<ProductEntity> optProduct = productRepo.findById(paymentOut.getProduct().getIdProduct());

            if (optProduct.isPresent()) {
                ProductEntity product = optProduct.get();
                paymentOut.calculateTotal(product.getTax());

                List<PaymentOutDetailEntity> detalles = new ArrayList<>();

                double capitalMoney = (paymentOut.getLoamAmount() / paymentOut.getLoamTerm());

                double monthTaxRate = capitalMoney * (BDUtil.getTaxTemp(product.getTax()));

                double monthPay = capitalMoney + monthTaxRate;

                for (int i = 1; i <= paymentOut.getLoamTerm(); i++){

                    monthPay = new BigDecimal(monthPay).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    monthTaxRate = new BigDecimal(monthTaxRate).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    capitalMoney = new BigDecimal(capitalMoney).setScale(2, RoundingMode.HALF_UP).doubleValue();

                    PaymentOutDetailEntity cuota = new PaymentOutDetailEntity();

                    cuota.setPaymentOut(paymentOut);
                    cuota.setCount(i);
                    cuota.setCapitalMoney(capitalMoney);
                    cuota.setDatePayment(paymentOut.getDatePaymentOut().plusMonths(i));
                    cuota.setTaxRate(monthTaxRate);
                    cuota.setMonthlyPay(monthPay);

                    detalles.add(cuota);
                }

                paymentOut.setDetails(detalles);

                return repo.save(paymentOut);
            }

            return null;

        } catch (Exception e){
            throw new ServiceException(e);
        }


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
