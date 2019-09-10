package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Calificacion
 *
 */
@Entity

public class Calificacion implements Serializable {

	   
	@Id
	private int idCalificacion;
	private static final long serialVersionUID = 1L;

	public Calificacion() {
		super();
	}   
	public int getIdCalificacion() {
		return this.idCalificacion;
	}

	public void setIdCalificacion(int idCalificacion) {
		this.idCalificacion = idCalificacion;
	}
   
}
