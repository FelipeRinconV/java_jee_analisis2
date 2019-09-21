package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Entity implementation class for Entity: Calificacion
 * 
 *
 */
@Entity
@Table(name = "Calificaciones")
public class Calificacion implements Serializable {

	@ManyToOne
	private Producto producto;

	@ManyToOne
	private Usuario usuario;

	//La calificacion va ser de 1 a 10 validar ESTE CAMPO
	@Min(1)
	@Max(10)
	@Column(name = "CALIFICACION")
	private int calificacion;

	@Id
	@Column(name = "ID_CALIFICACION")
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

}
