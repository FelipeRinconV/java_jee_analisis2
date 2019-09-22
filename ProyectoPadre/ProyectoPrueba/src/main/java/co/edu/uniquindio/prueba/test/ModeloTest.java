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
	
	//--------------FIN DE METODOS DE PRODUCTOS
	
	
	
	//--------------INICIO DE METODOS DE COMPRA
	
	

}
