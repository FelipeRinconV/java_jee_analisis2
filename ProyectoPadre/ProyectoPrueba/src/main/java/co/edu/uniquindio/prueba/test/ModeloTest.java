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

		Persona persona = entityManager.find(Persona.class, "1");

		Assert.assertNotNull(persona);

		Producto producto = new Producto();

		producto.setTipo(Tipo.DEPORTE);

		producto.setPersona(persona);

		producto.setPrecio(33);

		producto.setNombre("Nuevo");

		producto.setIdProducto(80);

		producto.setUrlImagen("imagen");

		producto.setDisponibilidad(true);

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

		producto.setTipo(Tipo.DEPORTE);

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

		producto.setTipo(Tipo.DEPORTE);

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

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "producto.json" })
	public void buscarProducto() {

		Producto pro = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(pro);

	}

	// --------------FIN DE METODOS DE PRODUCTOS
	
	
	//-----INICIO DE METODOS DE FAVORITOS
	
	
	

}
