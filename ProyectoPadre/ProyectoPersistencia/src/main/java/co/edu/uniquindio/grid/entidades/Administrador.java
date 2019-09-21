package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 * Entidad que se encargara de representar los administradores de la pagina
 */
@Entity
public class Administrador extends Persona implements Serializable {
		
	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}
   
}
