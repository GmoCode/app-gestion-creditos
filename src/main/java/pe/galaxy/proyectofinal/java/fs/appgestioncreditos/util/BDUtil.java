package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util;

import static java.util.Objects.isNull;

public class BDUtil {

	public static String getLike(String str) {
		if (isNull(str)) {
			str = "";
		}
		return "%" + str + "%";
	}

<<<<<<< HEAD
	public static Double getTaxTemp(Double tx) {
		if (isNull(tx)) {
			tx = 0.0;
		}
		return (tx/100);
	}

=======
>>>>>>> 2677653 (V 1.0)
}
