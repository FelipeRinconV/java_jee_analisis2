package modelo;

import javax.persistence.Column;

import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Rol;
import javafx.beans.property.StringProperty;

public class UsuarioObservable {

	
	private StringProperty cedula;
	
	private StringProperty nombreCompleto;

	private StringProperty email;

	private StringProperty numeroTelefono;

	private StringProperty direccion;

	private StringProperty contraseña;
	
	private Rol rol;
	
	
	
	
	public UsuarioObservable(Persona persona) {
		
		
		// TODO Auto-generated constructor stub
	}

}
