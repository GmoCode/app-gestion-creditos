package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Generic;

import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T> {


    Optional<T> findByIdAndStatus(Long id) throws ServiceException;
    Optional<T> findById(Long id) throws ServiceException;

    List<T> findAllStatus() throws  ServiceException;

    List<T> findAll() throws  ServiceException;

    T save(T t) throws ServiceException;

    T update(T t) throws ServiceException;

    Boolean delete(T t) throws ServiceException;

}
