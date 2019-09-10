package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Punto
 *
 */
@Entity

@IdClass(PuntoPK.class)
public class Punto implements Serializable {

	   
	@Id
	private String longitud;   
	@Id
	private String latitud;
	private static final long serialVersionUID = 1L;

	public Punto() {
		super();
	}   
	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}   
	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
   
}
