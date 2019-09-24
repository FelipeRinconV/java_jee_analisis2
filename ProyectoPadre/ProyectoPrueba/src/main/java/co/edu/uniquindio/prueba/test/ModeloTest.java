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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

	// INICIO DE PRUEBAS DE LA ENTIDAD ADMNISTRADOR

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void agregarAdministradorTest() {

		Administrador admin = new Administrador();

		admin.setCedula("100");

		admin.setNombreCompleto("Jhon gnumeroTelefonoutierrez");

		admin.setNumeroTelefono("21399");

		admin.setContraseña("contra");

		admin.setDireccion("Quimbaya");

		admin.setEmail("admin@gmail");

		Administrador admin2 = entityManager.find(Administrador.class, admin.getCedula());

		Assert.assertNull(admin2);

		entityManager.persist(admin);

		admin2 = entityManager.find(Administrador.class, "100");

		Assert.assertNotNull(admin2);

	}

	/**
	 * Test para actualizar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarAdministradorTest() {

		Administrador admin = new Administrador();

		admin.setCedula("100");

		admin.setNombreCompleto("Jhon gnumeroTelefonoutierrez");

		admin.setNumeroTelefono("21399");

		admin.setContraseña("contra");

		admin.setDireccion("Quimbaya");

		admin.setEmail("admin@gmail");

		entityManager.persist(admin);

		Administrador admin2 = entityManager.find(Administrador.class, "100");

		admin2.setNombreCompleto("Nuevo");

		entityManager.merge(admin2);

		Administrador admin3 = entityManager.find(Administrador.class, "100");

		Assert.assertEquals("Nuevo", admin3.getNombreCompleto());

	}

	/**
	 * Test para eliminar una usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarAdministradorTest() {

		Administrador admin = new Administrador();

		admin.setCedula("100");

		admin.setNombreCompleto("Jhon gnumeroTelefonoutierrez");

		admin.setNumeroTelefono("21399");

		admin.setContraseña("contra");

		admin.setDireccion("Quimbaya");

		admin.setEmail("admin@gmail");

		entityManager.persist(admin);

		entityManager.remove(admin);

		Administrador admin2 = entityManager.find(Administrador.class, "100");

		Assert.assertNull(admin2);

	}

	/**
	 * Permite probar la busqueda de una persona por su cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void buscarAdministrador() {
		Administrador empleado = entityManager.find(Administrador.class, "500");
		Assert.assertEquals("jc@gmail.com", empleado.getEmail());
	}

	/**
	 * Permite probar el listar todas las personas tanto a usuarios como
	 * administradores
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void listarPersonasTest() {
		Query query = entityManager.createQuery("select p from Persona p");
		int tamanio = query.getResultList().size();
		Assert.assertEquals(tamanio, 3);
	}

	// INICIO DE PRUEBAS DE PRODUCTOS
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void insertarProductoTest() {

		Persona empleado = entityManager.find(Persona.class, "500");

		Assert.assertNotNull(empleado);

		Producto producto = new Producto();

		producto.setNombre("Mac ffff");

		producto.setIdProducto(80);

		producto.setUrlImagen("IMffAGEN");

		producto.setDisponibilidad(true);

		producto.setTipo(Categoria.DEPORTE);

		producto.setPersona(empleado);

		producto.setPrecio(33);

		producto.setDescripcion("mejor mac del mercado");

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

		Persona empleado = entityManager.find(Persona.class, "500");

		Assert.assertNotNull(empleado);

		Producto producto = new Producto();

		producto.setNombre("Mac ffff");

		producto.setIdProducto(10);

		producto.setUrlImagen("IMffAGEN");

		producto.setDisponibilidad(true);

		producto.setTipo(Categoria.DEPORTE);

		producto.setPersona(empleado);

		producto.setPrecio(33);

		producto.setDescripcion("mejor mac del mercado");

		entityManager.persist(producto);

		Producto pCambio = entityManager.find(Producto.class, 10);

		Assert.assertEquals("IMffAGEN", pCambio.getUrlImagen());

		pCambio.setUrlImagen("nueva_url");

		entityManager.merge(pCambio);

		Assert.assertEquals("nueva_url", pCambio.getUrlImagen());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void eliminarProducto() {

		Persona empleado = entityManager.find(Persona.class, "500");

		Assert.assertNotNull(empleado);

		Producto producto = new Producto();

		producto.setNombre("Mac xxx");

		producto.setIdProducto(22);

		producto.setUrlImagen("imagen");

		producto.setDisponibilidad(true);

		producto.setTipo(Categoria.DEPORTE);

		producto.setPersona(empleado);

		producto.setPrecio(33);

		producto.setDescripcion("disfrutale");

		entityManager.persist(producto);

		Producto pCambio = entityManager.find(Producto.class, 22);

		Assert.assertNotNull(pCambio);

		entityManager.remove(pCambio);
		pCambio = entityManager.find(Producto.class, 22);
		Assert.assertNull(pCambio);

	}

	// --------------FIN DE METODOS DE PRODUCTOS

	// --------------INICIO DE METODOS DE COMPRA

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void insertarCompraTest() {

		Usuario empleado = entityManager.find(Usuario.class, "100");

		Assert.assertNotNull(empleado);

		Compra compra = new Compra();

		compra.setIdCompra(10);

		compra.setFechaCompra(new Date());

		compra.setUsuario(empleado);

		compra.setTipoPago(TipoPago.EFECTIVO);

		entityManager.persist(compra);

		Compra p = entityManager.find(Compra.class, 10);

		Assert.assertNotNull(p);

	}

	// --------------INICIO DE METODOS DE Usuario

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarUsuarioTest() {
		Usuario user = new Usuario();
		user.setCedula("345");
		user.setNombreCompleto("jose rodriguez");
		user.setNumeroTelefono("44343");
		user.setContraseña("4354");
		user.setDireccion("43654");
		user.setEmail("jose@gmail.com");
		Usuario user2 = entityManager.find(Usuario.class, user.getCedula());

		Assert.assertNull(user2);

		entityManager.persist(user);

	}

	/**
	 * Test para actualizar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json"})
	public void actualizarUsuarioTest() {

		Usuario user2 = entityManager.find(Usuario.class, "100");

		Assert.assertNotNull(user2);
		
		user2.setNombreCompleto("Nuevo");

		entityManager.merge(user2);

		Usuario user3 = entityManager.find(Usuario.class, "100");

		Assert.assertEquals("Nuevo", user3.getNombreCompleto());

	}

	/**
	 * Test para eliminar una usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarUsuarioTest() {

		Usuario user = new Usuario();

		user.setCedula("800");

		user.setNombreCompleto("Jhon gnumeroTelefonoutierrez");

		user.setNumeroTelefono("21399");

		user.setContraseña("contra");

		user.setDireccion("Quimbaya");

		user.setEmail("admin@gmail");

		entityManager.persist(user);

		entityManager.remove(user);

		Usuario user2 = entityManager.find(Usuario.class, "800");

		Assert.assertNull(user2);

	}

	// -----------------FIN METODOS DE USUARIO

	// ----------------INICIO METODO DE CALIFICACION
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void insertarCalificacionTest() {
		Usuario empleado = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 2);

		Assert.assertNotNull(empleado);
		Assert.assertNotNull(producto);

		Calificacion calificacion = new Calificacion();

		calificacion.setCalificacion(4);
		calificacion.setIdCalificacion(23);
		calificacion.setProducto(producto);
		calificacion.setUsuario(empleado);

		entityManager.persist(calificacion);

		Calificacion c = entityManager.find(Calificacion.class, 23);

		Assert.assertNotNull(c);

	}

	/**
	 * Permite probar la actualizacion de una calificacion cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "comentario.json", "producto.json" })
	public void actuaizarCalificacion() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Calificacion calificacion = new Calificacion();

		calificacion.setIdCalificacion(3);

		calificacion.setCalificacion(3);

		calificacion.setUsuario(usuario);

		calificacion.setProducto(producto);

		entityManager.persist(calificacion);

		Calificacion calificacion2 = entityManager.find(Calificacion.class, 3);

		Assert.assertEquals(3, calificacion2.getCalificacion());

		calificacion2.setCalificacion(4);

		entityManager.merge(calificacion2);

		Assert.assertEquals(4, calificacion2.getCalificacion());

	}

	/**
	 * Metodo que permiter eliminar una calificacion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void eliminarCalificacion() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Calificacion calificacion = new Calificacion();

		calificacion.setCalificacion(2);
		calificacion.setIdCalificacion(6);
		calificacion.setProducto(producto);
		calificacion.setUsuario(usuario);

		entityManager.persist(calificacion);

		Calificacion pCalificacion = entityManager.find(Calificacion.class, 6);

		Assert.assertNotNull(pCalificacion);

		entityManager.remove(pCalificacion);
		pCalificacion = entityManager.find(Calificacion.class, 6);
		Assert.assertNull(pCalificacion);

	}

	// ----------FIN METODOS DE CALIFICACION
	// ----------INICIO METODOS DE COMENTARIO
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void insertarComentarioTest() {
		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setIdComentario(5);
		comentario.setComentario("bueno");
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);

		entityManager.persist(comentario);

		Comentario c = entityManager.find(Comentario.class, 5);

		Assert.assertNotNull(c);

	}

	/**
	 * Permite probar la actualizacion de un comentario cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "comentario.json", "producto.json" })
	public void actuaizarComentario() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setIdComentario(5);
		comentario.setComentario("bueno");
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);
		entityManager.persist(comentario);

		Comentario comentario2 = entityManager.find(Comentario.class, 5);

		Assert.assertEquals("bueno", comentario2.getComentario());

		comentario2.setComentario("malo");

		entityManager.merge(comentario2);

		Assert.assertEquals("malo", comentario2.getComentario());

	}

	/**
	 * Metodo que permiter eliminar una comentario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void eliminarComentario() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setIdComentario(5);
		comentario.setComentario("bueno");
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);
		entityManager.persist(comentario);

		Comentario pComentario = entityManager.find(Comentario.class, 5);

		Assert.assertNotNull(pComentario);

		entityManager.remove(pComentario);
		pComentario = entityManager.find(Comentario.class, 5);
		Assert.assertNull(pComentario);

	}
	// -----------FIN METODOS DE COMENTARIO

}
