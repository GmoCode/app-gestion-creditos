package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.seguridad;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final UserEntity userEntity;

	public UserDetailsImpl(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getAuthorities().stream().map(
        			authority -> new SimpleGrantedAuthority(authority.getNameAuthority())
        		).collect(Collectors.toList());
    }
    
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override

	public String getUsername() {
		return userEntity.getUserName();
	}

	@Override

	public boolean isAccountNonExpired() {
		return true;
	}

	@Override

	public boolean isAccountNonLocked() {
		return true;
	}

	@Override

	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override

	public boolean isEnabled() {
		return true;
	}

	public User getUserDetails() {
		return new User(userEntity.getUserName(),userEntity.getPassword(), this.getAuthorities());
	}
}
