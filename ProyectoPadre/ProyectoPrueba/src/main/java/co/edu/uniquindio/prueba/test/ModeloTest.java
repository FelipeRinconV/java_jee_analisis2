package co.edu.uniquindio.prueba.test;

import co.edu.uniquindio.grid.entidades.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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

	// INICIO DE PRUEBAS DE LA ENTIDAD ADMNISTRADOR

	@Test
	@Transactional(value = TransactionMode.COMMIT)
	/**
	 * Metodo para probar la insercion de los administradores
	 */
	public void agregarAdministradorTest() {

		Administrador admin = new Administrador();

		admin.setCedula("100");

		admin.setNombreCompleto("Jhon gnumeroTelefonoutierrez");

		admin.setNumeroTelefono("21399");

		admin.setContraseña("contra");

		admin.setDireccion("Quimbaya");

		admin.setEmail("admin@gmail");

		Administrador admin2 = entityManager.find(Administrador.class, "100");

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
	@UsingDataSet({"persona.json"})
	public void insertarProductoTest() {

		Producto nuevoP = new Producto();

		
		
		
		nuevoP.setFechaLimite(new Date());
		
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		nuevoP.setCalificaciones(calificaciones);

		
		List<Comentario> comentarios = new ArrayList<Comentario>();
		nuevoP.setComentarios(comentarios);

		nuevoP.setDescripcion("Nuevo producto de mac");

		List<DetalleCompra> detallesCompra = new ArrayList<DetalleCompra>();
	
		nuevoP.setDetallesCompra(detallesCompra);
		
		nuevoP.setDisponibilidad(false);

		List<Favorito> favoritos = new ArrayList<Favorito>();
		nuevoP.setFavoritos(favoritos);

		nuevoP.setNombre("Mac 12");

		Administrador admin2 = entityManager.find(Administrador.class, "500");
		
		nuevoP.setPersona(admin2);

		nuevoP.setPrecio(200);

		nuevoP.setTipo(Categoria.TECNOLOGIA);

		nuevoP.setUrlImagen("imagen");

		Producto p1 = entityManager.find(Producto.class,nuevoP.getIdProducto() );

		Assert.assertNull(p1);

		entityManager.persist(nuevoP);

		p1 = entityManager.find(Producto.class,nuevoP.getIdProducto() );
	     
		Assert.assertNotNull(p1);
	}

}
