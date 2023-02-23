package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JWTHTTPConfigurer extends AbstractHttpConfigurer<JWTHTTPConfigurer, HttpSecurity> {
	
	private final JWTUtils jwtUtils;

    @Override
    public void configure(HttpSecurity http) {
        final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.antMatcher(Constants.LOGIN_URL)
        .addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtils));
    }



}
