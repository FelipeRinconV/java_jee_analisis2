package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comentario
 *
 */
@Entity
@Table(name = "Comentarios")
public class Comentario implements Serializable {

	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Producto producto;
	
	@Id
	@Column(name = "ID_COMENTARIO")
	private int idComentario;
	
	@Column(length = 300,nullable = false)
	private String comentario;
	
	private static final long serialVersionUID = 1L;

	public Comentario() {
		super();
	}   
	public int getDiComentario() {
		return this.idComentario;
	}

	public void setDiComentario(int diComentario) {
		this.idComentario = diComentario;
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
	public int getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
   
}
