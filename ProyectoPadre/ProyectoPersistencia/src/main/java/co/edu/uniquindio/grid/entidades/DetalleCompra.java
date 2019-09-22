package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity
@Table(name = "DetallesCompras")
public class DetalleCompra implements Serializable {

	@ManyToOne
	private Producto producto;

	@ManyToOne
	private Compra compra;

	@Id
	@Column(name = "ID_DETALLE")
	private long idDetalle;

	@Column(name = "VALOR_COMPRA")
	private double valorCompra;

	@Column(name = "CANTIDAD_PRODUCTO")
	private int cantidadProducto;

	@Column(name = "PRECIO_VENTA_PRODUCTO")
	private double precioVenta;

	private static final long serialVersionUID = 1L;

	public DetalleCompra() {
		super();
	}

	public long getId_detalle() {
		return this.idDetalle;
	}

	public void setId_detalle(long id_detalle) {
		this.idDetalle = id_detalle;
	}

	public int getCantidadProducto() {
		return this.cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public double getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
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
