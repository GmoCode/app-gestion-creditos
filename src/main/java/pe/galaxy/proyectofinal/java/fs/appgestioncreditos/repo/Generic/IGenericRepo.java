package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.repo.Generic;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface IGenericRepo <T,ID> extends JpaRepository<T,ID> {

}
