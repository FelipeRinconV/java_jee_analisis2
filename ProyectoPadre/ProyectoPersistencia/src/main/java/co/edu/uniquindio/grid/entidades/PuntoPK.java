package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;

/**
 * ID class for entity: Punto
 *
 */ 
public class PuntoPK  implements Serializable {   
   
	         
	private String longitud;         
	private String latitud;
	private static final long serialVersionUID = 1L;

	public PuntoPK() {}

	

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
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PuntoPK)) {
			return false;
		}
		PuntoPK other = (PuntoPK) o;
		return true
			&& (getLongitud() == null ? other.getLongitud() == null : getLongitud().equals(other.getLongitud()))
			&& (getLatitud() == null ? other.getLatitud() == null : getLatitud().equals(other.getLatitud()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getLongitud() == null ? 0 : getLongitud().hashCode());
		result = prime * result + (getLatitud() == null ? 0 : getLatitud().hashCode());
		return result;
	}
   
   
}
