package co.edu.uniquindio.prueba.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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

	@Test
	@org.jboss.arquillian.transaction.api.annotation.Transactional(value = TransactionMode.COMMIT)
	public void generarTest() {

		
		
	}
}
