package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.UserEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration.IUserRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IUserService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service

public class UserServiceImpl implements IUserService {

    public IUserRepo repo;

    public UserServiceImpl(final IUserRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public Optional<UserEntity> findByIdAndStatus(Long id) throws ServiceException {
        try {
            return repo.findByIdAndStatus(id);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserEntity> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserEntity> findAllStatus() throws ServiceException {
        return repo.findAllStatus();
    }

    @Override
    public List<UserEntity> findAll() throws ServiceException {
        return repo.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) throws ServiceException {
        return repo.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) throws ServiceException {
        try{
            UserEntity oUserEntity = this.findById(userEntity.getIdUser()).orElse(null);
            if(!Objects.isNull(oUserEntity)){
                BeanUtils.copyProperties(userEntity,oUserEntity);
                return repo.save(oUserEntity);
            }
        }catch (Exception e){
            throw new ServiceException(e);
        }
        return null;
    }

    @Transactional
    @Override
    public Boolean delete(UserEntity userEntity) throws ServiceException {
        try {
            UserEntity oUserEntity = this.findById(userEntity.getIdUser()).orElse(null);
            if(!Objects.isNull(oUserEntity)){
                repo.delete(oUserEntity);
            }
        }catch (Exception e){
            throw new ServiceException(e);
        }
        return null;
    }
}
