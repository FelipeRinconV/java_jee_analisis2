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

	/**
	 * Metodo que permite probar la insercion de administradores
	 */
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

		Administrador administrador = new Administrador();

		administrador.setCedula("100");

		administrador.setNombreCompleto("Luz andrea pelaes");

		administrador.setNumeroTelefono("0000000");

		administrador.setContraseña("12");

		administrador.setDireccion("Aremnia");

		administrador.setEmail("ad@gmail.com");

		entityManager.persist(administrador);

		Administrador admin2 = entityManager.find(Administrador.class, "100");

		admin2.setNombreCompleto("andres");

		entityManager.merge(admin2);

		Administrador admin3 = entityManager.find(Administrador.class, "100");

		Assert.assertEquals("andres", admin3.getNombreCompleto());

	}

	/**
	 * Test para eliminar una usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarAdministradorTest() {

		Administrador admin = new Administrador();

		admin.setContraseña("contrasenia");

		admin.setDireccion("Armenia");

		admin.setEmail("admin@gmail");

		admin.setCedula("1200");

		admin.setNombreCompleto("Alejandra peleaz");

		admin.setNumeroTelefono("21399");

		entityManager.persist(admin);
		
		Administrador admin2 = entityManager.find(Administrador.class, "1200");

		Assert.assertNotNull(admin2);

		entityManager.remove(admin);

		admin2 = entityManager.find(Administrador.class, "1200");

		Assert.assertNull(admin2);

	}

	/**
	 * Permite probar la busqueda de una persona por su cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void buscarAdministrador() {
		Administrador empleado = entityManager.find(Administrador.class, "2");
		Assert.assertEquals("al@gmail.com", empleado.getEmail());
	}

}
