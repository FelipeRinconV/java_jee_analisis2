package co.edu.uniquindio.grid.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Enumeracion de categorias en la que se pueden clasificar los productos
 * 
 * @author felipe
 *
 */
@Entity
@NamedQueries({
	
	@NamedQuery(name = Categoria.LISTAR_TIPOS,query ="select t from Categoria t" )
})
public class Categoria {

	
	public final static String  LISTAR_TIPOS="listar_tipos";
	
	
	@OneToMany(mappedBy = "tipo")	
	private List<Producto> productos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String tipo;

	public Categoria(List<Producto> productos, int id, String tipo) {
		super();
		this.productos = productos;
		this.id = id;
		this.tipo = tipo;
	}

	public Categoria() {
		super();
	}
	
	

	public Categoria(String tipo) {
		super();
		this.tipo = tipo;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [productos=" + productos + ", id=" + id + ", tipo=" + tipo + "]";
	}


	
	
	
}
