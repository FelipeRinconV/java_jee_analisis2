package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 *
 */
@Entity

public class Producto implements Serializable {

     @ManyToOne
     private Persona persona;
	
	
	@Id
	private String codProducto;
	private static final long serialVersionUID = 1L;

	public Producto() {
		super();
	}   
	public String getCodProducto() {
		return this.codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
   
}
