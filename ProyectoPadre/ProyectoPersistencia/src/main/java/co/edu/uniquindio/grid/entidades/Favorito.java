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
   
}
