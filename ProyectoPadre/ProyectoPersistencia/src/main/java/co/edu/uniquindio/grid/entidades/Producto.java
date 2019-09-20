package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 * Entidad que representa a un producto
 */

@Entity
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PRODUCTO")
	private long idProducto;

	@Column(nullable = false)
	private String urlImagen;
	
	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private double precio;
	
	@Column(nullable = false)
	private boolean disponibilidad;
	
	@Temporal(TemporalType.DATE)
	private Date fechaLimite;
	
	@Column(nullable = false)
	private String nombre;


	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@ManyToOne
	private Persona persona;

	@OneToMany(mappedBy = "producto")
	private List<Calificacion> calificaciones;

	@OneToMany(mappedBy = "producto")
	private List<Compra> compras;

	
	@OneToMany(mappedBy = "producto")
	private List<Favorito> favoritos;

	@OneToMany(mappedBy = "producto")
	private List<Comentario> comentarios;

	private static final long serialVersionUID = 1L;

	public Producto() {
		super();
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}


	public String getUrlImagen() {
		return urlImagen;
	}


	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
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

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
