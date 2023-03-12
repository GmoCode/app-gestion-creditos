package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.seguridad;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppTest {

	public static void main(String[] args) {
		
		System.out.println(new BCryptPasswordEncoder().encode("12345678"));

	}

}
