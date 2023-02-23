package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.seguridad;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTUtils {

	public String generateJwtToken(Authentication authentication) {

		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		
		 Collection<? extends GrantedAuthority> authorities=userPrincipal.getAuthorities();
			
		//System.out.println("Authorities -> "+ authorities);
		

		Collection<?> authoritiesItems= authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList()); 
		
		String tk2 = Jwts.builder().setIssuedAt(new Date()).setIssuer(Constants.ISSUER_INFO)
				.setSubject(userPrincipal.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
				.claim(Constants.AUTHORITIES, authoritiesItems)
				.signWith(SignatureAlgorithm.HS512, Constants.SUPER_SECRET_KEY).compact();
		
		System.out.println("tk2 -> "+tk2);
		return tk2;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(Constants.SUPER_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		System.out.println("authToken "+authToken);
		try {
			Jwts.parser().setSigningKey(Constants.SUPER_SECRET_KEY).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}