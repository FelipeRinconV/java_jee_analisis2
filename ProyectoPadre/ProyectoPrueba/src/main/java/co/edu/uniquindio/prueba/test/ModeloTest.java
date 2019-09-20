package co.edu.uniquindio.prueba.test;

import co.edu.uniquindio.grid.entidades.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.glassfish.internal.api.AdminAccessController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Usuario;

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

//	@Test
//	@org.jboss.arquillian.transaction.api.annotation.Transactional(value = TransactionMode.COMMIT)
//	public void generarTest() {
//
//	}

	// INICIO DE PRUEBAS CRUD

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
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

		entityManager.persist(admin);

		Administrador admin2 = entityManager.find(Administrador.class, "100");

		Assert.assertNotNull(admin2);

	}

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

}
