package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 * ENTIDAD QUE REPRESENTA A LOS ADMISTRADORES DE LA PAGINA SE UTILIZARO PORSTERIORME PARA LA SEGURIDAD 
 */
@Entity
public class Administrador extends Persona implements Serializable {
		
	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}
   
}
