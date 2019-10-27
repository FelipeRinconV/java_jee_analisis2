package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Descuento
 *
 */
@Entity
@Table(name = "Descuentos")
@NamedQueries({ @NamedQuery(name = Descuento.LISTAR_DESCUENTOS, query = "select d from Descuento d") })
public class Descuento implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LISTAR_DESCUENTOS = "listar_todos_los_descuentos";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DESCUENTO")
	private int id;

	@ManyToOne
	private Administrador administrador;

	@Column(name = "ACTIVO")
	private boolean activo;

	@Column(name = "CATEGORIA")
	private Categoria categoria;

	/**
	 * Porcentaje de descuento sobre el valor del producto
	 */
	@Column(name = "PORCENTAJE")
	private double porcentaje;

	public Descuento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Descuento other = (Descuento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

}
