package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.exception.ServiceException;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.seguridad.UserRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IVendorService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service

public class VendorServiceImpl implements IVendorService {

    public UserRepo repo;

    public VendorServiceImpl(UserRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public Optional<UserEntity> findByIdAndStatus(Long id) throws ServiceException {
        try {
            return repo.findByIdAndStatus(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserEntity> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserEntity> findAllStatus() throws ServiceException {

            return repo.findAllStatus().stream().sorted(Comparator.comparing(UserEntity::getIdUser)).collect(Collectors.toList());

    }

    @Override
    public List<UserEntity> findAll() throws ServiceException {

           return repo.findAll().stream().sorted(Comparator.comparing(UserEntity::getIdUser)).collect(Collectors.toList());
    }

    @Override
    public UserEntity save(UserEntity userEntity) throws ServiceException {

        return repo.save(userEntity);

    }

    @Override
    public UserEntity update(UserEntity userEntity) throws ServiceException {
        try {
            UserEntity oUserEntity = this.findById(userEntity.getIdUser()).orElse(null);
            if(!Objects.isNull(oUserEntity)){
                BeanUtils.copyProperties(userEntity,oUserEntity);
                return repo.save(userEntity);
            }
            return null;
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public Boolean delete(UserEntity userEntity) throws ServiceException {
        try {
            repo.delete(userEntity.getIdUser());
            return true;
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
