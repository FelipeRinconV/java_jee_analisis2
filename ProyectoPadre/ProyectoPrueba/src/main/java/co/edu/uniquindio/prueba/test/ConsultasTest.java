package co.edu.uniquindio.prueba.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.grid.entidades.*;

@RunWith(Arquillian.class)
public class ConsultasTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Persona.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	//
	@Test
	@UsingDataSet({ "persona.json", "producto.json" })
	public void porbarConsultaUsuarioCompra() {

		TypedQuery<Object[]> q = entityManager.createNamedQuery(Usuario.COMPRAS_USUARIO, Object[].class);

		
		Assert.assertNotNull(q);
		
		for (Object[] arr : q.getResultList()) {

			System.out.println(arr[0].toString() + " " + arr[1].toString());

		}
	}

}
