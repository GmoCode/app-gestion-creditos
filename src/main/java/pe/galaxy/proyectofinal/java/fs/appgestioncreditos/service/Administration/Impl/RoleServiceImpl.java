package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.RoleEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration.IRoleRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IRoleService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service

public class RoleServiceImpl implements IRoleService {

    public IRoleRepo repo;

    public RoleServiceImpl(IRoleRepo repo) {
        super();
        this.repo = repo;
    }


    @Override
    public Optional<RoleEntity> findByIdAndStatus(Long id) throws ServiceException {
          try {
            return repo.findByIdAndStatus(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<RoleEntity> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RoleEntity> findAllStatus() throws ServiceException {
        return repo.findAllStatus();
    }

    @Override
    public List<RoleEntity> findAll() throws ServiceException {
        return repo.findAll();
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) throws ServiceException {
        return repo.save(roleEntity);
    }

    @Override
    public RoleEntity update(RoleEntity roleEntity) throws ServiceException {
        try {
            RoleEntity oRoleEntity= this.findById(roleEntity.getIdRole()).orElse(null);
            if (!Objects.isNull(oRoleEntity)) {

                BeanUtils.copyProperties(roleEntity,oRoleEntity);
                return repo.save(oRoleEntity);
            }

        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Transactional
    @Override
    public Boolean delete(RoleEntity roleEntity) throws ServiceException {
        try {
            RoleEntity oRoleEntity = this.findById(roleEntity.getIdRole()).orElse(null);
            if(!Objects.isNull(oRoleEntity)){
                repo.delete(roleEntity.getIdRole());
                return true;
            }
            return null;
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }
}
