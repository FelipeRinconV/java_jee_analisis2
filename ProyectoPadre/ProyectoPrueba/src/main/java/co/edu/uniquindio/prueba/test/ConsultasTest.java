package co.edu.uniquindio.prueba.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

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

import co.edu.uniquindio.grid.dto.UsuarioGastosComprasDTO;
import co.edu.uniquindio.grid.dto.UsuarioRegistrosDTO;
import co.edu.uniquindio.grid.entidades.*;
import co.uniquindio.grid.dto.utils.Utilidades;

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

		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.BUSCAR_PERSONA_POR_EMAIL_Y_CONTRASENIA,
				Persona.class);

		query.setParameter("contra", "1234");

		query.setParameter("email", "usuario1@gmail.com");

		List<Persona> personas = query.getResultList();

		Assert.assertEquals(1, personas.size());

	}

	/**
	 * Prueba de listar todas las personsa que esten registradas en la base de datos
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	public void contarAdministradores() {

		TypedQuery<Long> query = entityManager.createNamedQuery(Administrador.CONTAR_ADMINISTRADORES, Long.class);

		Long numeroadmin = query.getSingleResult();

		Assert.assertEquals(new Double(numeroadmin), new Double(1));

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

		TypedQuery<Long> query = entityManager.createNamedQuery(Compra.TOTAl_COMPRAS_UNICAS, Long.class);

		Long total = query.getSingleResult();

		Assert.assertEquals(new Long(total), new Long(3));

	}

	/**
	 * CONSULTA N_2 Prueba de la consulta que permita contar el número de productos
	 * que hay por cada tipo de producto.
	 */
	@Test
	@UsingDataSet({ "persona.json", "calificacion.json", "producto.json", "compra.json", "comentario.json" })
	public void darCantidadProdcutosPorTipo() {

		TypedQuery<Long> query = entityManager.createNamedQuery(Producto.CANTIDAD_PRODUCTOS_POR_TIPO, Long.class);

		List<Long> cantidad = query.getResultList();

//		for (Long cant : cantidad) {
//
//			System.out.println("Cantidad: " + cant);
//		}

		Assert.assertEquals(2, cantidad.size());

	}

	/**
	 * Permite probar la consulta que permita determinar qué productos no tienen
	 * comentarios.
	 */
	@Test
	@UsingDataSet({ "persona.json", "calificacion.json", "producto.json", "compra.json", "comentario.json" })
	public void darProductosSinComentarios() {

		TypedQuery<Producto> query = entityManager.createNamedQuery(Producto.PRODUCTOS_SIN_COMENTARIOS, Producto.class);

		List<Producto> productos = query.getResultList();

		Assert.assertNotEquals(0, productos.size());

//		for (Producto product : productos) {
//
//			System.out.println(product.toString());
//
//		}

		Assert.assertEquals(1, productos.size());

	}

	/**
	 * Metodo para probar los usuarios con gmail
	 */

	@Test
	@UsingDataSet({ "persona.json", "calificacion.json", "producto.json", "compra.json", "comentario.json" })
	public void darUsuariosConGmail() {

	}

	/**
	 * Metodo para devolver la cantidad de registros que tenga el producto query N_5
	 */
	@Test
	@UsingDataSet({ "persona.json", "calificacion.json", "producto.json", "compra.json", "comentario.json" })
	public void darCantidadProductosPorUsuario() {

		TypedQuery<UsuarioRegistrosDTO> query = entityManager.createNamedQuery(Usuario.USUARIO_NUMERO_REGISTROS,
				UsuarioRegistrosDTO.class);

		List<UsuarioRegistrosDTO> lista = query.getResultList();

		// FUNCIONA
		for (UsuarioRegistrosDTO us : lista) {

			System.out.println(us.toString());
		}

	}

	/**
	 * metodo que prueba el query que permite calcular el valor total que ha gastado
	 * un usuario en compras n_6
	 */
	@Test
	@UsingDataSet({ "persona.json", "calificacion.json", "producto.json", "compra.json", "comentario.json",
			"detalles.json" })
	public void darTotalGastoComprasUsuario() {

		TypedQuery<UsuarioGastosComprasDTO> query = entityManager
				.createNamedQuery(Compra.TOTAL_GASTO_EN_COMPRAS_POR_USUARIO, UsuarioGastosComprasDTO.class);

		query.setParameter("id", "200");

		// FUNCIONA BIEN

//		List<UsuarioGastosComprasDTO> lista = query.getResultList();
//
//		for (UsuarioGastosComprasDTO us : lista) {
//
//			System.out.println(us.toString());
//		}

	}

	/**
	 * Cree una consulta que permita determinar cuál es el tipo de producto que
	 * tiene más registros. n_7
	 */
	@Test
	@UsingDataSet({ "favorito.json", "persona.json", "calificacion.json", "producto.json", "compra.json",
			"comentario.json", "detalles.json" })
	public void darTipoMasProductos() {

		TypedQuery<Categoria> query = entityManager.createNamedQuery(Producto.TIPO_PRODUCTO_MAS_REGISTROS,
				Categoria.class);

		// Funciona
//		List<Categoria> lista = query.getResultList();
//
//		for (Categoria us : lista) {
//
//			System.out.println(us.toString());
//		}

	}

	/**
	 * consulta que devuelve el producto más costoso que se ha publicado. n_8 se da
	 * un order by por precio y se saca el primer resultado se carca desc para que
	 * se de mayor a menor
	 */
	@Test
	@UsingDataSet({ "favorito.json", "persona.json", "calificacion.json", "producto.json", "compra.json",
			"comentario.json", "detalles.json" })
	public void darProdcutoMasCaro() {

		TypedQuery<Producto> query = entityManager.createNamedQuery(Producto.PRODUCTO_MAS_COSTOSO, Producto.class);

		query.setMaxResults(1);

		List<Producto> lista = query.getResultList();

//     FUNCIONA
//		for (Producto us : lista) {
//
//			System.out.println(us.toString());
//		}

	}

	/**
	 * consulta que devuelva una lista de Compras hechas entre dos fechas
	 * determinadas y que además hayan sido pagadas por un método de pago
	 * específico.
	 */

	@Test
	@UsingDataSet({ "favorito.json", "persona.json", "calificacion.json", "producto.json", "compra.json",
			"comentario.json", "detalles.json" })
	public void darComprasRangoFechaPagoEspecifico() {

		TypedQuery<Compra> query = entityManager.createNamedQuery(Compra.COMPRA_FECHAS_METODO_PAGO, Compra.class);

		Timestamp fecha1 = Utilidades.DateToTimestamp(new Date("2019-00-28"));

		Timestamp fecha2 = Utilidades.DateToTimestamp(new Date("2019-11-28"));

		query.setParameter("inicial", fecha1, TemporalType.TIMESTAMP);

		query.setParameter("final", fecha2, TemporalType.TIMESTAMP);

		query.setParameter("tipo", TipoPago.EFECTIVO);

		List<Compra> lista = query.getResultList();

		for (Compra us : lista) {

			System.out.println(us.toString());
		}

	}

	/**
	 * Metodo que busca que permite probar una compra de un producto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void darProductoMasCostosoPorTipoTest() {

		TypedQuery<Producto> query = entityManager.createNamedQuery(Producto.PRODUCTO_MAS_COSTOSOS_POR_TIPO,
				Producto.class);

		query.setMaxResults(1);

		query.setParameter("tipo", Categoria.TECNOLOGIA);

		Producto pro = query.getSingleResult();

		Assert.assertEquals(pro.getIdProducto(), 2);

	}

	/**
	 * prueba del query que da los detalles y las compras de una persona dada su
	 * cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void darCompraAndDetallesCompra() {

		TypedQuery<Object> query = entityManager.createNamedQuery(Compra.COMPRAS_DETALLES_POR_PERSONA, Object.class);

		query.setParameter("cedula", "200");

		List<Object> compraydetalles = query.getResultList();

		Assert.assertNotNull(compraydetalles);

	}

}
