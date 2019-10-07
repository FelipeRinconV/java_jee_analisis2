package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Entity implementation class for Entity: Calificacion
 * ENTIDAD QUE REPRESENTA LAS CALIFICACIONES QUE LE AH DADO UN PRODUCTO AL USUARIO
 *
 */
@Entity
@Table(name = "Calificaciones")
public class Calificacion implements Serializable {

	@ManyToOne
	private Producto producto;

	@ManyToOne
	private Usuario usuario;

	//La calificacion va ser de 1 a 10 validar ESTE CAMPO EN EL DAO
	@Min(1)
	@Max(10)
	@Column(name = "CALIFICACION",nullable = false)
	private int calificacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCalificacion;
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
		Calificacion other = (Calificacion) obj;
		if (idCalificacion != other.idCalificacion)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Calificacion [producto=" + producto.getNombre() + ", usuario=" + usuario.getNombreCompleto() + ", calificacion=" + calificacion
				+ ", idCalificacion=" + idCalificacion + "]";
	}
	
	
	
	

}
