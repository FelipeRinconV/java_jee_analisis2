package co.edu.uniquindio.prueba.test;

import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

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

	/**
	 * Prueba de listar todas las personsa que esten registradas en la base de datos
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	public void listarPersonas() {

		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_PERSONAS, Persona.class);

		List<Persona> personas = query.getResultList();

		Assert.assertEquals(3, personas.size());

	}

	/**
	 * Metodo para prbar la busqueda de una persona por su email
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	public void buscarPersonaPorEmail() {

		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.BUSCAR_POR_EMAIL, Persona.class);

		query.setParameter("email", "jc@gmail.com");

		List<Persona> personas = query.getResultList();

		Assert.assertEquals(1, personas.size());

	}

	/**
	 * Metodo que permite la prueba de dar las calificaciones de un producto por su
	 * id
	 */
	@Test
	@UsingDataSet({ "persona.json", "calificacion.json", "producto.json", "compra.json", "comentario.json" })
	public void darCalificacionesProductoPorId() {

		TypedQuery<Calificacion> query = entityManager.createNamedQuery(Producto.CALIFICACIONES_POR_ID,
				Calificacion.class);

		query.setParameter("id", 2);

		List<Calificacion> calificaciones = query.getResultList();

//		for (Calificacion a : calificaciones) {
//
//			System.out.println(a.toString());
//
//		}

		Assert.assertEquals(1, calificaciones.size());

	}

	/**
	 * Prueba la consulta de que permita determinar el número de compras (únicas)
	 * que se han realizado.
	 */
	@Test
	@UsingDataSet({ "persona.json", "calificacion.json", "producto.json", "compra.json", "comentario.json" })
	public void darNumeroDeConsultas() {

		TypedQuery<Integer> query = entityManager.createNamedQuery(Compra.TOTAl_COMPRAS_UNICAS, Integer.class);

	}

}
