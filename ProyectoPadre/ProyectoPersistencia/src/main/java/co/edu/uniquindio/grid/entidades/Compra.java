package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compra
 *
 */
@Entity

public class Compra implements Serializable {

	   
	@Id
	private int idCompra;
	private static final long serialVersionUID = 1L;

	public Compra() {
		super();
	}   
	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
   
}
