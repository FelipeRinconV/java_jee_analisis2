package co.edu.uniquindio.unimarket.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.grid.entidades.Administrador;
import co.edu.uniquindio.grid.entidades.Calificacion;
import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.grid.entidades.Producto;
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

			// Creacion de usuarios

			Usuario Userfelipe = new Usuario();
			Userfelipe.setNumeroTelefono("3157581");
			Userfelipe.setContrasenia("contra");
			Userfelipe.setDireccion("Quimbaya");
			Userfelipe.setNombreCompleto("Felipe villegas");
			Userfelipe.setCedula("1040");
			Userfelipe.setEmail("villegas@gmail.com");

			Usuario UserAndrea = new Usuario();
			UserAndrea.setNumeroTelefono("3157581");
			UserAndrea.setContrasenia("contrasenia");
			UserAndrea.setDireccion("Quimbaya");
			UserAndrea.setNombreCompleto("Andrea viviana");
			UserAndrea.setCedula("101213");
			UserAndrea.setEmail("andrea@gmail.com");

			Usuario UserDiana = new Usuario();
			UserDiana.setNumeroTelefono("323423345");
			UserDiana.setContrasenia("contrasenia");
			UserDiana.setDireccion("Armenia");
			UserDiana.setNombreCompleto("Diana patricica");
			UserDiana.setCedula("21232323");
			UserDiana.setEmail("diana@gmail.com");

			// Registro de usuarios
			entityManager.persist(Userfelipe);
			entityManager.persist(UserDiana);
			entityManager.persist(UserAndrea);

			// -----------------------Creacion de lista de imagenes

			List<String> imagenMac = new ArrayList<String>();

			// Creacion de url para la imagen del producto mac
			String urlMac1 = "http://localhost:8080/mac1.jpg";
			String urlMac2 = "http://localhost:8080/mac2.jpg";
			String urlMac3 = "http://localhost:8080/mac3.jpg";
			String urlMac4 = "http://localhost:8080/mac4.jpg";
			imagenMac.add(urlMac1);
			imagenMac.add(urlMac2);
			imagenMac.add(urlMac3);
			imagenMac.add(urlMac4);

			List<String> monitorImagen = new ArrayList<String>();

			// Creacion de url para la imagen del producto monitor lg
			String urlMo1 = "http://localhost:8080/monitor1.jpg";
			String urlMo2 = "http://localhost:8080/monitor2.jpg";
			String urlMo3 = "http://localhost:8080/monitor3.jpg";
			String urlMo4 = "http://localhost:8080/monitor4.jpg";
			monitorImagen.add(urlMo1);
			monitorImagen.add(urlMo2);
			monitorImagen.add(urlMo3);
			monitorImagen.add(urlMo4);

			// Lista de imagenes para los tenis nike air
			List<String> imagenNike = new ArrayList<String>();

			// Creacion de url para la imagen del producto monitor lg
			String urlNike1 = "http://localhost:8080/air1.jpg";
			String urlNike2 = "http://localhost:8080/air2.jpg";
			String urlNike3 = "http://localhost:8080/air3.jpg";
			String urlNike4 = "http://localhost:8080/air4.jpg";

			imagenNike.add(urlNike1);
			imagenNike.add(urlNike2);
			imagenNike.add(urlNike3);
			imagenNike.add(urlNike4);

			// Lista de imagenes para los tenis
			List<String> imagenesA50 = new ArrayList<String>();

			// Creacion de url para la imagen del producto monitor lg
			String urlA501 = "http://localhost:8080/a501.jpg";
			String urlA502 = "http://localhost:8080/a502.jpg";
			String urlA503 = "http://localhost:8080/a503.jpg";
			String urlA504 = "http://localhost:8080/a504.jpg";

			imagenesA50.add(urlA501);
			imagenesA50.add(urlA502);
			imagenesA50.add(urlA503);
			imagenesA50.add(urlA504);

			// ------------------PRODUCTOS---------------------

			Producto mac = new Producto();
			Producto nike = new Producto();
			Producto monitor = new Producto();
			Producto a50 = new Producto();

			// Se crea el producto mac
			mac.setNombre("Macbook Retina Touch Bar 15 Mod 2019");
			mac.setUrlImagenes(imagenMac);
			mac.setDisponibilidad(true);
			mac.setFechaLimite(new Date());
			mac.setTipo(Categoria.TECNOLOGIA);
			mac.setCantidadProducto(5);
			mac.setPrecio(5000000);
			mac.setDescripcion("MacBook Pro retina touch bar 15,6 pulgadas Modelo 2019");

			// Se crea el producto monitor lg

			monitor.setNombre("Monitor lg 21 pulgadas");
			monitor.setUrlImagenes(monitorImagen);
			monitor.setDisponibilidad(true);
			monitor.setFechaLimite(new Date());
			monitor.setTipo(Categoria.TECNOLOGIA);
			monitor.setCantidadProducto(150);
			monitor.setPrecio(2500000);
			monitor.setDescripcion("Nuevo monitor ultra delgado de lg ");

			// Creacion del producto nike

			nike.setNombre("Air nike color");
			nike.setUrlImagenes(imagenNike);
			nike.setDisponibilidad(true);
			nike.setFechaLimite(new Date());
			nike.setTipo(Categoria.MODA);
			nike.setCantidadProducto(200);
			nike.setPrecio(150000);
			nike.setDescripcion("Nuevos nike air 2019 coleccion future");

			// Creacion del producto a50

			a50.setNombre("Celular a50 ");
			a50.setUrlImagenes(imagenesA50);
			a50.setDisponibilidad(true);
			a50.setFechaLimite(new Date());
			a50.setTipo(Categoria.TECNOLOGIA);
			a50.setCantidadProducto(180);
			a50.setPrecio(750000);
			a50.setDescripcion("Telefono samsung a 50 proccesador t30");

			// ----LISTAS DE PRODCUTOS
			List<Producto> productosFelipe = new ArrayList<Producto>();
			List<Producto> productosAndrea = new ArrayList<Producto>();
			List<Producto> productosDiana = new ArrayList<Producto>();

			productosFelipe.add(mac);
			productosFelipe.add(monitor);
			productosAndrea.add(a50);
			productosDiana.add(nike);

			// Asociaciones de producto y Usuarios
			mac.setUsuario(Userfelipe);
			monitor.setUsuario(Userfelipe);
			Userfelipe.setProductos(productosFelipe);

			a50.setUsuario(UserAndrea);
			UserAndrea.setProductos(productosAndrea);

			nike.setUsuario(UserDiana);
			UserDiana.setProductos(productosDiana);

			// Persistimos los productos

			entityManager.persist(mac);
			entityManager.persist(monitor);
			entityManager.persist(nike);
			entityManager.persist(a50);

			// ---------CALIFICCACIONES

			// Creacion de calificaciones

			List<Calificacion> calificacionesFelipe = new ArrayList<Calificacion>();
			List<Calificacion> calificacionesAndrea = new ArrayList<Calificacion>();
			List<Calificacion> calificacionesDiana = new ArrayList<Calificacion>();

			// Creacion de calificaciones

			Calificacion calificacionMac = new Calificacion();
			Calificacion calificacionNike = new Calificacion();
			Calificacion calificacionMonitor = new Calificacion();
			Calificacion calificacionA50 = new Calificacion();

			// Se crean las calificaciones
			calificacionA50.setCalificacion(2);
			calificacionA50.setProducto(a50);
			calificacionA50.setUsuario(UserAndrea);
			calificacionesAndrea.add(calificacionA50);
			UserAndrea.setCalificaciones(calificacionesAndrea);
			a50.setCalificaciones(calificacionesAndrea);
			

			// Asociasiones de producto y usuarios en calificaciones de andra y mac
			calificacionNike.setCalificacion(3);
			calificacionNike.setUsuario(UserDiana);
			calificacionNike.setProducto(nike);
			calificacionesDiana.add(calificacionNike);
			UserDiana.setCalificaciones(calificacionesDiana);
			nike.setCalificaciones(calificacionesAndrea);

			// Asociasiones de producto y usuarios en calificaciones de felipe,a50 y nike
			calificacionMac.setCalificacion(5);
			calificacionMonitor.setCalificacion(2);
			calificacionMac.setProducto(mac);
			calificacionMac.setUsuario(Userfelipe);
			calificacionMonitor.setProducto(monitor);
			calificacionMonitor.setUsuario(Userfelipe);
			calificacionesFelipe.add(calificacionMac);
			calificacionesFelipe.add(calificacionMonitor);
			Userfelipe.setCalificaciones(calificacionesFelipe);
			mac.setCalificaciones(calificacionesFelipe);
			monitor.setCalificaciones(calificacionesFelipe);

			// Persistimos las calificaciones
			entityManager.persist(calificacionA50);
			entityManager.persist(calificacionMonitor);
			entityManager.persist(calificacionMac);
			entityManager.persist(calificacionNike);

		}

	}

	/**
	 * Default constructor.
	 */
	public SetupEJB() {
		// TODO Auto-generated constructor stub
	}

}
