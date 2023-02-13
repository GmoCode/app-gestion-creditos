package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ClientEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.administration.IClientRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IClientService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service

public class ClientServiceImpl implements IClientService {

    public IClientRepo repo;

    public ClientServiceImpl(IClientRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public Optional<ClientEntity> findByIdAndStatus(Long id) throws ServiceException {
        try {
            return repo.findByIdAndStatus(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ClientEntity> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ClientEntity> findAllStatus() throws ServiceException {

            return repo.findAllStatus();

    }

    @Override
    public List<ClientEntity> findAll() throws ServiceException {

           return repo.findAll();
    }

    @Override
    public ClientEntity save(ClientEntity clientEntity) throws ServiceException {

        return repo.save(clientEntity);

    }

    @Override
    public ClientEntity update(ClientEntity clientEntity) throws ServiceException {
        try {
            ClientEntity oClientEntity = this.findById(clientEntity.getIdClient()).orElse(null);
            if(!Objects.isNull(oClientEntity)){
                BeanUtils.copyProperties(clientEntity,oClientEntity);
                return repo.save(clientEntity);
            }
            return null;
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public Boolean delete(ClientEntity clientEntity) throws ServiceException {
        try {
            repo.delete(clientEntity.getIdClient());
            return true;
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
