package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comentario
 * ENTIDAD QUE REPRESENTA EL COMENTARIO QUE HACE UN USUARIO DE UN PRODUCTO
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idComentario;
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
		Comentario other = (Comentario) obj;
		if (idComentario != other.idComentario)
			return false;
		return true;
	}
	
	
	
   
}
