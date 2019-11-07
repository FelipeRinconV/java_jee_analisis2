package co.uniquindio.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "holaMundoBean")
@ApplicationScoped
public class HolaMundoBean {

	// Toda las variables deben tener get y set
	private String mensaje;

	public String getMensaje() {

		mensaje = "Hola texto desde bean";

		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
