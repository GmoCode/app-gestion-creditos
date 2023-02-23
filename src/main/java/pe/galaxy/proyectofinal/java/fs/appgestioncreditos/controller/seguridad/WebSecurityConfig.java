package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.seguridad;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true) // Adapter(Old)
//@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	private UserDetailsService userDetailsService;
	private JWTAuthEntryPoint jWTAuthEntryPoint;
	private JWTUtils jwtUtils;

	public WebSecurityConfig(UserDetailsService userDetailsService, JWTAuthEntryPoint jWTAuthEntryPoint,
			JWTUtils jwtUtils) {
		this.userDetailsService = userDetailsService;
		this.jWTAuthEntryPoint = jWTAuthEntryPoint;
		this.jwtUtils = jwtUtils;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/* .configurationSource(corsConfigurationSource()) */
		// http.cors(cors -> cors.disable());

		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(jWTAuthEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll().anyRequest().authenticated();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(jWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		http.apply(new JWTHTTPConfigurer(jwtUtils));

		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authProvider;
	}

	@Bean // 2)
	public JWTAuthorizationFilter jWTAuthorizationFilter() {
		return new JWTAuthorizationFilter();
	}

	@Bean // 1)
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*");
			}
		};
	}

	/*
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		log.info("corsConfigurationSource...");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(asList("*"));
		configuration.setAllowedMethods(asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(asList("Authorization", "Cache-Control", "Content-Type"));

		source.registerCorsConfiguration("/**", configuration);

		// source.registerCorsConfiguration("/**", new
		// CorsConfiguration().applyPermitDefaultValues());
		return source;

	}

*/
	/*
	@Bean
	public CorsConfigurationSource corsConfigurationSource2() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); //
		// Arrays.asList("*") //configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization",
				"Access-Control-Allow-Origin", "Cache-Control", "Content-Type"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;

	}
*/
}