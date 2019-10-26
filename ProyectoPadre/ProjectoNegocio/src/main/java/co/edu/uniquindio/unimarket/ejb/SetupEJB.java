package co.edu.uniquindio.unimarket.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.grid.entidades.Administrador;
import co.edu.uniquindio.grid.entidades.Calificacion;
import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.grid.entidades.Comentario;
import co.edu.uniquindio.grid.entidades.Compra;
import co.edu.uniquindio.grid.entidades.Favorito;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.grid.entidades.Rol;
import co.edu.uniquindio.grid.entidades.Usuario;

/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void init() {

		TypedQuery<Long> query = entityManager.createNamedQuery(Administrador.CONTAR_ADMINISTRADORES, Long.class);

		Long numeroAdmins = query.getSingleResult();

		if (numeroAdmins == 0) {

			// Agregamos un nuevo administrador por defecto

			Administrador admin = new Administrador();

			admin.setCedula("1007376425");
			admin.setContrasenia("root");
			admin.setEmail("feliperv04@gmail.com");
			admin.setDireccion("Quimbaya");
			admin.setNumeroTelefono("1082737238");
			admin.setNombreCompleto("Luis felipe rincon villegas ");

			entityManager.persist(admin);

			// Agregamos datos de prueba para la aplicacion de escritorio

			Usuario usuario1 = new Usuario();

			List<Favorito> favoritos = new ArrayList<Favorito>();
			usuario1.setFavoritos(favoritos);
			usuario1.setNumeroTelefono("3157581");
			List<Calificacion> calificaciones = new ArrayList<Calificacion>();
			usuario1.setCalificaciones(calificaciones);
			List<Comentario> comentarios = new ArrayList<Comentario>();
			usuario1.setComentario(comentarios);
			List<Compra> compras = new ArrayList<Compra>();
			usuario1.setCompras(compras);
			usuario1.setContrasenia("contraseña");
			usuario1.setDireccion("Quimbaya");
			usuario1.setNombreCompleto("Luis felipe rincon");
			usuario1.setCedula("1040");
			usuario1.setEmail("erdiv@gmail.com");

			entityManager.persist(usuario1);

			Usuario usuario2 = new Usuario();

			List<Favorito> favoritos2 = new ArrayList<Favorito>();
			usuario2.setFavoritos(favoritos);
			usuario2.setNumeroTelefono("3157581");
			List<Calificacion> calificaciones2 = new ArrayList<Calificacion>();
			usuario2.setCalificaciones(calificaciones);
			List<Comentario> comentarios2 = new ArrayList<Comentario>();
			usuario2.setComentario(comentarios);
			List<Compra> compras2 = new ArrayList<Compra>();
			usuario2.setCompras(compras);
			usuario2.setContrasenia("contrasenia");
			usuario2.setDireccion("Quimbaya");
			usuario2.setNombreCompleto("Andrea viviana");
			usuario2.setCedula("101213");
			usuario2.setEmail("andrea@gmail.com");

			entityManager.persist(usuario2);

			// Registro de productos para pruebas

			Producto producto = new Producto();
			producto.setNombre("Macbook Retina Touch Bar 15 Mod 2019");
			List<String> imagenes = new ArrayList<String>();

			String urlMac1 = "http://localhost:8080/mac1.jpg";
			String urlMac2 = "http://localhost:8080/mac2.jpg";
			String urlMac3 = "http://localhost:8080/mac3.jpg";
			String urlMac4 = "http://localhost:8080/mac4.jpg";

			imagenes.add(urlMac1);
			imagenes.add(urlMac2);
			imagenes.add(urlMac3);
			imagenes.add(urlMac4);

			producto.setUrlImagenes(imagenes);
			producto.setDisponibilidad(true);
			producto.setFechaLimite(new Date());
			producto.setTipo(Categoria.TECNOLOGIA);
			producto.setCantidadProducto(5);
			producto.setUsuario(usuario1);
			producto.setPrecio(5000000);
			producto.setDescripcion("MacBook Pro retina touch bar 15,6 pulgadas Modelo 2019");

			entityManager.persist(producto);

//			Producto producto2 = new Producto();
//			producto2.setNombre("Diamante 1");
//			List<String> imagenes2 = new ArrayList<String>();
//			producto2.setUrlImagenes(imagenes2);
//			producto2.setDisponibilidad(true);
//			producto2.setTipo(Categoria.JOYAS);
//			producto2.setUsuario(usuario1);
//			producto2.setPrecio(33);
//			producto2.setDescripcion("Piedra unica en el pais");
//
//			entityManager.persist(producto2);

		}

	}

	/**
	 * Default constructor.
	 */
	public SetupEJB() {
		// TODO Auto-generated constructor stub
	}

}
