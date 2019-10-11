package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto ENTIDAD QUE REPRESENTA LSO
 * PRODUCTOS QUE SE VENDERAN EN LA TIENDA UNIMARKET
 */

@Entity
@Table(name = "Productos")
@NamedQueries({
		@NamedQuery(name = Producto.CALIFICACIONES_POR_ID, query = "select p from Producto pr, IN (pr.calificaciones) p where pr.idProducto=:id"),
		@NamedQuery(name = Producto.CANTIDAD_PRODUCTOS_POR_TIPO, query = "select count(p) from Producto p group by p.tipo"),
		@NamedQuery(name = Producto.PRODUCTOS_SIN_COMENTARIOS, query = "select p from Producto p where p.comentarios is empty"),
		@NamedQuery(name = Producto.TIPO_PRODUCTO_MAS_REGISTROS, query = "select  max(p.tipo) from Producto p "),
		@NamedQuery(name = Producto.PECIO_PRODUCTO_MAS_CARO, query = "select p.nombre,max(p.precio) from Producto p "),
		@NamedQuery(name = Producto.PRODUCTO_MAS_COSTOSO, query = "select p From Producto p order by p.precio desc"),

		@NamedQuery(name = Producto.PRODUCTO_MAS_COSTOSOS_POR_TIPO, query = "select P  from Producto p  where p.tipo=:tipo  order by p.precio desc"),
		@NamedQuery(name = Producto.PRODUCTOS_DISPONIBLES, query = "select p From Producto p where p.disponibilidad=TRUE and p.fechaLimite <= :fechaActual")

})
public class Producto implements Serializable {

	@ManyToOne
	private Usuario usuario;

	/**
	 * Lista los productos DISPONIBLES segun fecha y cantidad
	 */

	public static final String PRODUCTOS_DISPONIBLES = "listar_productos_disponibles";

	/*
	 * consulta que permita determinar cuál es el TIPO de producto que tiene más
	 * registros.
	 */
	public static final String TIPO_PRODUCTO_MAS_REGISTROS = "producto_mas_registros";

	/**
	 * consulta que devuelva el PRECIO del producto más costoso que se ha publicado.
	 */
	public static final String PECIO_PRODUCTO_MAS_CARO = "precio_mas_costoso";

	/**
	 * consulta que devuelva el PRODUCTO más costoso que se ha publicado.
	 */
	public static final String PRODUCTO_MAS_COSTOSO = "producto_mas_costoso";

	/**
	 * Cree una consulta que devuelve el producto más costoso por cada tipo de
	 * producto.
	 */

	public static final String PRODUCTO_MAS_COSTOSOS_POR_TIPO = "producto_mas_costoso_por_tipos";

	/**
	 * Devuelve la CANTIDAD de productos por determinado tipo
	 */
	public static final String CANTIDAD_PRODUCTOS_POR_TIPO = "cantidad_producto_por_tipo";

	/**
	 * Da las CALIFICACIONES de un producto dado su id
	 */
	public static final String CALIFICACIONES_POR_ID = "calificaciones_por_id";

	/**
	 * Devuleve una LISTA DE PRODUCTOS los cuales no tienen comentarios
	 */
	public static final String PRODUCTOS_SIN_COMENTARIOS = "productos_sin_comentario";

	@Id
	@Column(name = "ID_PRODUCTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;

	/**
	 * Lista de las url de las imagenes
	 */
    @ElementCollection
	private List<String> urlImagenes;

	@Column(nullable = false, length = 330)
	private String descripcion;

	@Column(nullable = false)
	private int precio;

	@Column(nullable = false)
	private boolean disponibilidad;

	/**
	 * Fecha limite de disponibilidad
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaLimite;

	@Column(nullable = false)
	private String nombre;

	@Enumerated(EnumType.STRING)
	private Categoria tipo;

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

	public List<String> getUrlImagenes() {
		return urlImagenes;
	}

	public void setUrlImagenes(List<String> urlImagenes) {
		this.urlImagenes = urlImagenes;
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

	public Categoria getTipo() {
		return tipo;
	}

	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	@Override
	public String toString() {
		return "Producto [usuario=" + usuario.getNombreCompleto() + ", idProducto=" + idProducto + ", precio=" + precio
				+ ", disponibilidad=" + disponibilidad + ", fechaLimite=" + fechaLimite + ", nombre=" + nombre
				+ ", tipo=" + tipo;
	}

}
