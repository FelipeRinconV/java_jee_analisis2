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
public class Usuario extends Persona implements Serializable {

	private String codVendedor;

	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

	public String getCodVendedor() {
		return this.codVendedor;
	}

	public void setCodVendedor(String codVendedor) {
		this.codVendedor = codVendedor;
	}

}
