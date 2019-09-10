package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Favorito
 *
 */
@Entity

public class Favorito implements Serializable {

	   
	@Id
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
