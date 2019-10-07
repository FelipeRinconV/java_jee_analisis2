package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Favorito
 * ENTIDAD PARA GUARDAR LOS PRODUCTOS FAVORITOS DE LOS USUARIOS
 */
@Entity
@Table(name = "Favoritos")
public class Favorito implements Serializable {

	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Producto producto;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FAVORITO")
	private int idFavorito;
	
	private static final long serialVersionUID = 1L;

	public Favorito() {
		super();
	}   
	public int getIdFavorito() {
		return this.idFavorito;
	}

	public void setIdFavorito(int idFavorito) {
		this.idFavorito = idFavorito;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		result = prime * result + idFavorito;
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
		Favorito other = (Favorito) obj;
		if (idFavorito != other.idFavorito)
			return false;
		return true;
	}
	
	
	
   
}
