package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.repo.seguridad.UserRepo;

import static java.util.Objects.isNull;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity= this.userRepo.findByUserName(username);
		
		if (isNull(userEntity)) {
			throw new UsernameNotFoundException("Usuario no existe");
		}
		
		return new UserDetailsImpl(userEntity);
		
		//return new User(usuarioEntity.getUsuario(),usuarioEntity.getClave(),emptyList());
	
	} 

}
