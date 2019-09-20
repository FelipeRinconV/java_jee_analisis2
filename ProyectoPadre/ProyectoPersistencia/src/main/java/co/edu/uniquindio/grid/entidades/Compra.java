package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compra
 *
 * Entidad que representara la compra agregar la fecha valor de la compra el
 * usuario y vendedor
 */


@Entity
public class Compra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompra;

	
	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Producto producto;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCompra;
	
	/**
	 * Pegruntar al profesor si es necesario o se puede sacar del producto
	 */
    @Column(nullable = false)
	private double valorCompra;
	 
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
