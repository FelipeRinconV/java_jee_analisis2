package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.ejb.Timeout;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 *
 */

@Entity
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;

	
	private String imagen;

	private String descripcion;

	private int precio;

	private boolean disponibilidad;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaLimite;

	private String nombre;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@ManyToOne
	private Persona persona;

	@OneToMany(mappedBy = "producto")
	private List<Calificacion> calificaciones;

	@OneToMany(mappedBy = "producto")
	private List<DetalleCompra> detallesCompra;

	@OneToMany(mappedBy = "producto")
	private List<Favorito> favoritos;

	@OneToMany(mappedBy = "producto")
	private List<Comentario> comentarios;

	private static final long serialVersionUID = 1L;

	public Producto() {
		super();
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getUrlImagen() {
		return imagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.imagen = urlImagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public List<Favorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DetalleCompra> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProducto;
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
			Producto other = (Producto) obj;
			if (idProducto != other.idProducto)
				return false;
			return true;
		}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	
	
}
