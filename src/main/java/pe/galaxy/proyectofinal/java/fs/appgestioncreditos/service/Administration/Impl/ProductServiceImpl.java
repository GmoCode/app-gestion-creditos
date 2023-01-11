package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ClientEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ProductEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration.IProductRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IProductService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    public IProductRepo repo;

    public ProductServiceImpl(final IProductRepo repo) {
        super();

        this.repo = repo;
    }

    @Override
    public Optional<ProductEntity> findByIdAndStatus(Long id) throws ServiceException {
        try {
            return repo.findByIdAndStatus(id);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ProductEntity> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ProductEntity> findAllStatus() throws ServiceException {
        return repo.findAllStatus();
    }

    @Override
    public List<ProductEntity> findAll() throws ServiceException {
        return repo.findAll();
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) throws ServiceException {

        return repo.save(productEntity);
    }

    @Override
    public ProductEntity update(ProductEntity productEntity) throws ServiceException {
        try {
            ProductEntity oProductEntity = this.findById(productEntity.getIdProduct()).orElse(null);
            if(!Objects.isNull(oProductEntity)){
                BeanUtils.copyProperties(productEntity,oProductEntity);
                return repo.save(productEntity);
            }
            return null;
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public Boolean delete(ProductEntity productEntity) throws ServiceException {
        try {
            ProductEntity oProductEntity = this.findById(productEntity.getIdProduct()).orElse(null);
            if(!Objects.isNull(oProductEntity)){
                 repo.delete(productEntity);
                 return true;
            }
            return null;

        }catch (Exception e){
            throw new ServiceException(e);
        }

    }
}
