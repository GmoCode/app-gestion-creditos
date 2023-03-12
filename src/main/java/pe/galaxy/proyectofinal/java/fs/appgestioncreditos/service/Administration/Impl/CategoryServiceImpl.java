package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.CategoryEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.repo.administration.ICategoryRepo;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.ICategoryService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.exception.ServiceException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service

public class CategoryServiceImpl implements ICategoryService {

    public ICategoryRepo repo;

    public CategoryServiceImpl(ICategoryRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public Optional<CategoryEntity> findByIdAndStatus(Long id) throws ServiceException {
        try {
            return repo.findByIdAndStatus(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CategoryEntity> findAllStatus() throws ServiceException {

            return repo.findAllStatus().stream().sorted(Comparator.comparing(CategoryEntity::getIdCategory)).collect(Collectors.toList());

    }

    @Override
    public List<CategoryEntity> findAll() throws ServiceException {

           return repo.findAll().stream().sorted(Comparator.comparing(CategoryEntity::getIdCategory)).collect(Collectors.toList());
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) throws ServiceException {

        return repo.save(categoryEntity);

    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) throws ServiceException {
        try {
            CategoryEntity oCategoryEntity = this.findById(categoryEntity.getIdCategory()).orElse(null);
            if(!Objects.isNull(oCategoryEntity)){
                BeanUtils.copyProperties(categoryEntity,oCategoryEntity);
                return repo.save(categoryEntity);
            }
            return null;
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public Boolean delete(CategoryEntity categoryEntity) throws ServiceException {
        try {
            repo.delete(categoryEntity.getIdCategory());
            return true;
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
