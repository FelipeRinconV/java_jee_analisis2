package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comentario
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
