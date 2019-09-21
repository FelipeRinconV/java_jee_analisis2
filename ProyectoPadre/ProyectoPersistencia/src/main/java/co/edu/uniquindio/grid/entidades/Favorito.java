package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Favorito
 *
 */
@Entity
@Table(name = "Favoritos")
public class Favorito implements Serializable {

	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Producto producto;
	
	@Id
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
   
}
