package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comentario
 *
 * Comentario de un producto debe tener el usuario y el producto asociado
 * 
 *
 */
@Entity

public class Comentario implements Serializable {

	   
	@Id
	private int diComentario;
	private static final long serialVersionUID = 1L;

	public Comentario() {
		super();
	}   
	public int getDiComentario() {
		return this.diComentario;
	}

	public void setDiComentario(int diComentario) {
		this.diComentario = diComentario;
	}
   
}
