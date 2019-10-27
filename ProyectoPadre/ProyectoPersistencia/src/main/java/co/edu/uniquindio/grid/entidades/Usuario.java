package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vendedor
 *
 */

@NamedQueries({

		@NamedQuery(name = Usuario.COMPRAS_USUARIO, query = "select u,p from Usuario u inner join u.productos p"),
		@NamedQuery(name = Usuario.USUARIOS_CON_GMAIL, query = "select u from Usuario u where u.email like '%gmail%'"),
		@NamedQuery(name = Usuario.USUARIO_NUMERO_REGISTROS, query = "select new co.edu.uniquindio.grid.dto.UsuarioRegistrosDTO(p.usuario.nombreCompleto,p.usuario.email,count(p.idProducto)) from Usuario user inner join user.productos p group by p.usuario"),
        @NamedQuery(name = Usuario.LISTAR_USUARIOS,query = "select u from Usuario u")
})

@Entity
@Table(name = "Usuarios")
public class Usuario extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12344;

	/**
	 * Devuelve  el numero de registro de un usuario
	 */
	public static final String USUARIO_NUMERO_REGISTROS = "numero_de_registro_por_usuario";

	/**
	 * Permite listar a los usuarios compradores y vendedores
	 */
	
	public static final String LISTAR_USUARIOS= "listar_usuarios";


	/**
	 * Permite listar los usuarios que tienen correo con gmail
	 */
	public static final String USUARIOS_CON_GMAIL = "usuarios_con_gmail";

	/**
	 * Permite listar las compras que han realizado los usarios
	 */
	public static final String COMPRAS_USUARIO = "compras_usuarios";

	/**
	 * Devuelve los productos asociados dado el id de un usuario
	 */
	public static final String PRODUCTOS_ASOCIADOS = "productos_asociados";

	//SOLO ME CENTRO ACA
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	private List<Producto> productos;

	@OneToMany(mappedBy = "usuario",orphanRemoval = true)
	private List<Calificacion> calificaciones;

	//HASTA ACA PARA LA ENTREGA DE ESCRITORIO
	@OneToMany(mappedBy = "usuario")
	private List<Compra> compras;

	@OneToMany(mappedBy = "usuario")
	private List<Favorito> favoritos;

	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentario;


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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Usuario [cedula=" + getCedula() +" nombre: " +getNombreCompleto()  + "]";
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	
}
