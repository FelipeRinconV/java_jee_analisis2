package co.edu.uniquindio.prueba.test;

import java.util.Date;

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

import com.sun.xml.ws.api.tx.at.Transactional;
import com.sun.xml.ws.tx.at.validation.TXAttributesValidator.TransactionAttributeType;

import co.edu.uniquindio.grid.entidades.Persona1;
import co.edu.uniquindio.grid.entidades.Sexo;

@RunWith(Arquillian.class)
public class ModeloTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Persona1.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@Test
	@org.jboss.arquillian.transaction.api.annotation.Transactional(value = TransactionMode.COMMIT)
	public void generarTest() {

		Persona1 person = new Persona1();

		person.setCedula("1003");
		
		person.setSexo(Sexo.FEMENIDO );
		
		person.setFecha(new Date());
		
		

		entityManager.persist(person);

	}
}
