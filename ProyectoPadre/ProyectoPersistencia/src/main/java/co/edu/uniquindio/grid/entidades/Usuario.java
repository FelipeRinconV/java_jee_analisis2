package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vendedor
 *
 */

@Entity
@Table(name = "Usuarios")
public class Usuario extends Persona implements Serializable {

	
	@OneToMany(mappedBy = "usuario")
	private List<Calificacion> calificaciones;
	
	@OneToMany(mappedBy = "usuario")
	private List<Compra> compras;
	
	@OneToMany(mappedBy = "usuario")
	private List<Favorito> favoritos;
	
	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentario;
	
	@Enumerated(EnumType.STRING)
    private Rol rol;
	
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Favorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}

	public List<Comentario> getComentario() {
		return comentario;
	}

	public void setComentario(List<Comentario> comentario) {
		this.comentario = comentario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
