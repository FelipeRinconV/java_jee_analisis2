package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Calificacion
 * 
 * Calificacion del producto debe tneer el usuario y el producto ademas de la
 * calificacion (restringir mas la calificacion)
 *
 */
@Entity

public class Calificacion implements Serializable {

	@ManyToOne
	private Producto producto;

	@ManyToOne
	private Usuario usuario;

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
