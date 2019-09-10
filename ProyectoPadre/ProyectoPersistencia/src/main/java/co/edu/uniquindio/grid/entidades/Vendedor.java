package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vendedor
 *
 */

@Entity
public class Vendedor extends Persona implements Serializable {

	@OneToMany(mappedBy = "vendedor")
	private List<Producto> productos;
	
	
	
	private String codVendedor;
	
	private static final long serialVersionUID = 1L;

	public Vendedor() {
		super();
	}   
	public String getCodVendedor() {
		return this.codVendedor;
	}

	public void setCodVendedor(String codVendedor) {
		this.codVendedor = codVendedor;
	}
   
}
