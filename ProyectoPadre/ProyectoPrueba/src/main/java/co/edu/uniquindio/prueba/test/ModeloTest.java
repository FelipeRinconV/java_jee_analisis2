package co.edu.uniquindio.prueba.test;

import co.edu.uniquindio.grid.entidades.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ModeloTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Persona.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@Test
	@org.jboss.arquillian.transaction.api.annotation.Transactional(value = TransactionMode.COMMIT)
	public void generarTest() {

	}

	/**
	 * Metodo que permite probar la insercion de administradores
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void agregarAdministradorTest() {

		Administrador nuevo = new Administrador();

		nuevo.setCedula("30");

		nuevo.setNumeroTelefono("21399");

		nuevo.setContraseña("contra");

		nuevo.setNombreCompleto("Andres");

		nuevo.setDireccion("Bogota");

		nuevo.setEmail("andres@gmail");

		Administrador admin = entityManager.find(Administrador.class, "30");

		Assert.assertNull(admin);

		entityManager.persist(nuevo);

		admin = entityManager.find(Administrador.class, "30");

		Assert.assertNotNull(admin);

	}

	/**
	 * devuelve un administrador dada su cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void buscarAdministrador() {
		Administrador empleado = entityManager.find(Administrador.class, "2");
		Assert.assertEquals("al@gmail.com", empleado.getEmail());
	}

	/**
	 * Test para actualizar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarAdministradorTest() {

		Administrador administrador = new Administrador();

		administrador.setCedula("200");

		administrador.setNombreCompleto("Luz andrea pelaes");

		administrador.setNumeroTelefono("0000000");

		administrador.setContraseña("12");

		administrador.setDireccion("Aremnia");

		administrador.setEmail("ad@gmail.com");

		entityManager.persist(administrador);

		Administrador admin = entityManager.find(Administrador.class, "200");

		admin.setNombreCompleto("andres");

		entityManager.merge(admin);

		Administrador admin3 = entityManager.find(Administrador.class, "200");

		Assert.assertEquals("andres", admin3.getNombreCompleto());

	}

	/**
	 * metodo que permite probar la eliminacion de un usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarAdministradorTest() {

		Administrador nuevo = new Administrador();

		nuevo.setContraseña("contrasenia");

		nuevo.setDireccion("Armenia");

		nuevo.setEmail("admin@gmail");

		nuevo.setCedula("1200");

		nuevo.setNombreCompleto("Alejandra peleaz");

		nuevo.setNumeroTelefono("21399");

		entityManager.persist(nuevo);

		Administrador admin = entityManager.find(Administrador.class, "1200");

		Assert.assertNotNull(admin);

		entityManager.remove(nuevo);

		admin = entityManager.find(Administrador.class, "1200");

		Assert.assertNull(admin);

	}

	/**
	 * Metodo para probar la insercion de un usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarUsuarioTest() {
		Usuario nuevo = new Usuario();

		nuevo.setNombreCompleto("aberto noriega");
		nuevo.setNumeroTelefono("1121212");

		nuevo.setCedula("800");
		nuevo.setContraseña("44444");

		nuevo.setDireccion("hjas");
		nuevo.setEmail("jose@gmail.com");

		Usuario user = entityManager.find(Usuario.class, nuevo.getCedula());

		Assert.assertNull(user);

		entityManager.persist(nuevo);

	}

	/**
	 * Test para actualizar un usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void actualizarUsuarioTest() {

		Usuario recuperado = entityManager.find(Usuario.class, "1");

		Assert.assertNotNull(recuperado);

		recuperado.setNombreCompleto("recuperado");

		entityManager.merge(recuperado);

		Usuario user = entityManager.find(Usuario.class, "1");

		Assert.assertEquals("recuperado", user.getNombreCompleto());

	}

	/**
	 * Test para eliminar un usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarUsuarioTest() {

		Usuario nuevo = new Usuario();

		nuevo.setCedula("800");
		nuevo.setContraseña("contra");

		nuevo.setDireccion("Quimbaya");

		nuevo.setEmail("admin@gmail");
		nuevo.setNombreCompleto("Jhon gnumeroTelefonoutierrez");

		nuevo.setNumeroTelefono("21399");
		entityManager.persist(nuevo);

		entityManager.remove(nuevo);

		Usuario user = entityManager.find(Usuario.class, "800");

		Assert.assertNull(user);

	}

	/**
	 * Busqueda de usuario por cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void buscarUsuario() {
		Usuario empleado = entityManager.find(Usuario.class, "1");
		Assert.assertEquals("user@gmail.com", empleado.getEmail());
	}

	// INICIO DE PRUEBAS DE PRODUCTOS

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void insertarProductoTest() {

		Usuario empleado = entityManager.find(Usuario.class, "1");
		
		Assert.assertNotNull(empleado);

		Producto producto = new Producto();

		producto.setTipo(Tipo.JOYAS);

		producto.setPersona(empleado);

		producto.setPrecio(33);

		producto.setNombre("Nuevo");

		producto.setIdProducto(80);

		producto.setUrlImagen("imagen");

		producto.setDisponibilidad(true);

		producto.setDescripcion("LA JOYA MAS BONITA");

		entityManager.persist(producto);

		Producto p = entityManager.find(Producto.class, 80);

		Assert.assertNotNull(p);

	}

	/**
	 * Permite probar la actualizacion de un producto en este caso se probo
	 * cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void actuaizarProducto() {

		Usuario empleado = entityManager.find(Usuario.class, "1");
		
		Assert.assertNotNull(empleado);

		Producto product = new Producto();


		product.setTipo(Tipo.JOYAS);

		product.setPersona(empleado);

		product.setPrecio(33);

		product.setNombre("Nuevo");

		product.setIdProducto(80);

		product.setUrlImagen("imagen");

		product.setDisponibilidad(true);

		product.setDescripcion("LA JOYA MAS BONITA");

		entityManager.persist(product);

		Producto pCambio = entityManager.find(Producto.class, 80);

		Assert.assertEquals("imagen", pCambio.getUrlImagen());

		pCambio.setUrlImagen("discoc");

		entityManager.merge(pCambio);

		Assert.assertEquals("discoc", pCambio.getUrlImagen());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void eliminarProducto() {

		Usuario empleado = entityManager.find(Usuario.class, "1");

		Assert.assertNotNull(empleado);

		Producto product = new Producto();


		product.setTipo(Tipo.JOYAS);

		product.setPersona(empleado);

		product.setPrecio(33);

		product.setNombre("Nuevo");

		product.setIdProducto(80);

		product.setUrlImagen("imagen");

		product.setDisponibilidad(true);

		product.setDescripcion("LA JOYA MAS BONITA");
		
		
		entityManager.persist(product);

		Producto cambioProducto = entityManager.find(Producto.class, 50);

		Assert.assertNotNull(cambioProducto);

		entityManager.remove(cambioProducto);
		
		cambioProducto = entityManager.find(Producto.class, 50);
		
		Assert.assertNull(cambioProducto);

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "producto.json" })
	public void buscarProducto() {

		Producto pro = entityManager.find(Producto.class, 2);

		Assert.assertNotNull(pro);

	}

	// --------------FIN DE METODOS DE PRODUCTOS revisado hasta aca
	
	
//	// ----------------INICIO METODO DE CALIFICACION
//		@Test
//		@Transactional(value = TransactionMode.ROLLBACK)
//		@UsingDataSet({ "persona.json", "producto.json" })
//		public void insertarCalificacionTest() {
//			Usuario emplea = entityManager.find(Usuario.class, "1");
//			Producto pro = entityManager.find(Producto.class, 2);
//
//			Assert.assertNotNull(emplea);
//			Assert.assertNotNull(pro);
//
//			Calificacion calificacion = new Calificacion();
//
//			calificacion.setIdCalificacion(50);
//			calificacion.setCalificacion(20);
//			calificacion.setProducto(pro);
//			calificacion.setUsuario(emplea);
//
//			entityManager.persist(calificacion);
//
//			Calificacion calificacion2 = entityManager.find(Calificacion.class, 23);
//
//			Assert.assertNotNull(calificacion2);
//
//		}
//
//		/**
//		 * Permite probar la actualizacion de una calificacion cambiando la url
//		 */
//		@Test
//		@Transactional(value = TransactionMode.ROLLBACK)
//		@UsingDataSet({ "persona.json", "comentario.json", "producto.json" })
//		public void actuaizarCalificacion() {
//
//			Usuario usuario = entityManager.find(Usuario.class, "100");
//			Producto producto = entityManager.find(Producto.class, 3);
//
//			Assert.assertNotNull(usuario);
//			Assert.assertNotNull(producto);
//
//			Calificacion calificacion = new Calificacion();
//
//			calificacion.setIdCalificacion(3);
//
//			calificacion.setCalificacion(3);
//
//			calificacion.setUsuario(usuario);
//
//			calificacion.setProducto(producto);
//
//			entityManager.persist(calificacion);
//
//			Calificacion calificacion2 = entityManager.find(Calificacion.class, 3);
//
//			Assert.assertEquals(3, calificacion2.getCalificacion());
//
//			calificacion2.setCalificacion(4);
//
//			entityManager.merge(calificacion2);
//
//			Assert.assertEquals(4, calificacion2.getCalificacion());
//
//		}
//
//		/**
//		 * Metodo que permiter eliminar una calificacion
//		 */
//		@Test
//		@Transactional(value = TransactionMode.ROLLBACK)
//		@UsingDataSet({ "persona.json", "producto.json" })
//		public void eliminarCalificacion() {
//
//			Usuario usuario = entityManager.find(Usuario.class, "100");
//			Producto producto = entityManager.find(Producto.class, 3);
//
//			Assert.assertNotNull(usuario);
//			Assert.assertNotNull(producto);
//
//			Calificacion calificacion = new Calificacion();
//
//			calificacion.setCalificacion(2);
//			calificacion.setIdCalificacion(6);
//			calificacion.setProducto(producto);
//			calificacion.setUsuario(usuario);
//
//			entityManager.persist(calificacion);
//
//			Calificacion pCalificacion = entityManager.find(Calificacion.class, 6);
//
//			Assert.assertNotNull(pCalificacion);
//
//			entityManager.remove(pCalificacion);
//			pCalificacion = entityManager.find(Calificacion.class, 6);
//			Assert.assertNull(pCalificacion);
//
//		}
//		
//		/**
//		 * Permite probar la busqueda de una calificacion por su id
//		 */
//		@Test
//		@Transactional(value = TransactionMode.ROLLBACK)
//		@UsingDataSet({ "calificaciones.json" })
//		public void buscarCalificacion() {
//			Calificacion calificacion = entityManager.find(Calificacion.class, 10);
//			Assert.assertEquals(10, calificacion.getCalificacion());
//		}

		// ----------FIN METODOS DE CALIFICACION
	
	
	

}
