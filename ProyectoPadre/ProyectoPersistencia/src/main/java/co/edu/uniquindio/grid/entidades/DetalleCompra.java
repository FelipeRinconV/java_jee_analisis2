package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity
public class DetalleCompra implements Serializable {

	@ManyToOne
	private Producto producto;

	@ManyToOne
	private Compra compra;

	private int cantidadProducto;

	private int precioVenta;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;

	private int valorCompra;

	private static final long serialVersionUID = 1L;

	public DetalleCompra() {
		super();
	}

	public int getId_detalle() {
		return this.idDetalle;
	}

	public void setId_detalle(int id_detalle) {
		this.idDetalle = id_detalle;
	}

	public int getCantidadProducto() {
		return this.cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public int getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(int valorCompra) {
		this.valorCompra = valorCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idDetalle ^ (idDetalle >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleCompra other = (DetalleCompra) obj;
		if (idDetalle != other.idDetalle)
			return false;
		return true;
	}

}
