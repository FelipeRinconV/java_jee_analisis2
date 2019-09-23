package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;


/**
 * Entidad que representa un usuario que pude ser un vendedor o un comprador
 *
 */

@Entity
public class Usuario extends Persona implements Serializable {

	@Enumerated(EnumType.STRING)
	private Rol rol;

	@OneToMany(mappedBy = "usuario")
	private List<Calificacion> calificaciones;

	@OneToMany(mappedBy = "usuario")
	private List<Compra> compras;

	@OneToMany(mappedBy = "usuario")
	private List<Favorito> favoritos;

	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentario;

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
