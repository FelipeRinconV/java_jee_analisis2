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

		Usuario empleado = entityManager.find(Usuario.class, "1");

		Assert.assertNotNull(empleado);

		Producto producto = new Producto();

		producto.setTipo(Tipo.JOYAS);

		producto.setPersona(empleado);

		producto.setPrecio(33);

		producto.setNombre("Nuevo");

		producto.setIdProducto(80);

		producto.setUrlImagen("imagen");

		producto.setDisponibilidad(true);

		producto.setDescripcion("LA JOYA MAS BONITA");

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

		Usuario empleado = entityManager.find(Usuario.class, "1");

		Assert.assertNotNull(empleado);

		Producto product = new Producto();

		product.setTipo(Tipo.JOYAS);

		product.setPersona(empleado);

		product.setPrecio(33);

		product.setNombre("Nuevo");

		product.setIdProducto(80);

		product.setUrlImagen("imagen");

		product.setDisponibilidad(true);

		product.setDescripcion("LA JOYA MAS BONITA");

		entityManager.persist(product);

		Producto pCambio = entityManager.find(Producto.class, 80);

		Assert.assertEquals("imagen", pCambio.getUrlImagen());

		pCambio.setUrlImagen("discoc");

		entityManager.merge(pCambio);

		Assert.assertEquals("discoc", pCambio.getUrlImagen());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void eliminarProducto() {

		Usuario empleado = entityManager.find(Usuario.class, "1");

		Assert.assertNotNull(empleado);

		Producto product = new Producto();

		product.setTipo(Tipo.JOYAS);

		product.setPersona(empleado);

		product.setPrecio(33);

		product.setNombre("Nuevo");

		product.setIdProducto(80);

		product.setUrlImagen("imagen");

		product.setDisponibilidad(true);

		product.setDescripcion("LA JOYA MAS BONITA");

		entityManager.persist(product);

		Producto cambioProducto = entityManager.find(Producto.class, 80);

		Assert.assertNotNull(cambioProducto);

		entityManager.remove(cambioProducto);

		cambioProducto = entityManager.find(Producto.class, 80);

		Assert.assertNull(cambioProducto);

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "producto.json" })
	public void buscarProducto() {

		Producto pro = entityManager.find(Producto.class, 2);

		Assert.assertNotNull(pro);

	}

	// --------------FIN DE METODOS DE PRODUCTOS revisado hasta aca

	// ----------------INICIO METODO DE CALIFICACION
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void insertarCalificacionTest() {
		Usuario emplea = entityManager.find(Usuario.class, "1");
		Producto pro = entityManager.find(Producto.class, 2);

		Assert.assertNotNull(emplea);
		Assert.assertNotNull(pro);

		Calificacion calificacion = new Calificacion();

		calificacion.setProducto(pro);
		calificacion.setUsuario(emplea);
		calificacion.setIdCalificacion(50);
		calificacion.setCalificacion(20);

		entityManager.persist(calificacion);

		Calificacion calificacion2 = entityManager.find(Calificacion.class, 50);

		Assert.assertNotNull(calificacion2);

	}

	/**
	 * Permite probar la actualizacion de una calificacion cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "comentario.json", "producto.json" })
	public void actuaizarCalificacion() {

		Producto product = entityManager.find(Producto.class, 3);
		Usuario user = entityManager.find(Usuario.class, "1");

		Assert.assertNotNull(user);
		Assert.assertNotNull(product);

		Calificacion calificacion = new Calificacion();

		calificacion.setUsuario(user);

		calificacion.setProducto(product);

		calificacion.setIdCalificacion(23);

		calificacion.setCalificacion(10);

		entityManager.persist(calificacion);

		Calificacion calificacionCambio = entityManager.find(Calificacion.class, 23);

		Assert.assertNotNull(calificacionCambio);

		calificacionCambio.setCalificacion(6);

		entityManager.merge(calificacionCambio);

		Assert.assertEquals(6, calificacionCambio.getCalificacion());

	}

	/**
	 * Metodo que permiter eliminar una calificacion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void eliminarCalificacion() {

		Usuario usuario = entityManager.find(Usuario.class, "1");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Calificacion calificacion = new Calificacion();

		calificacion.setCalificacion(9);
		calificacion.setIdCalificacion(9);
		calificacion.setProducto(producto);
		calificacion.setUsuario(usuario);

		entityManager.persist(calificacion);

		Calificacion cal2 = entityManager.find(Calificacion.class, 9);

		Assert.assertNotNull(cal2);

		entityManager.remove(cal2);

		cal2 = entityManager.find(Calificacion.class, 9);
		Assert.assertNull(cal2);

	}

	/**
	 * Permite probar la busqueda de una calificacion por su id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "calificaciones.json" })
	public void buscarCalificacion() {
		Calificacion calificacion = entityManager.find(Calificacion.class, 20);
		Assert.assertEquals(9, calificacion.getCalificacion());
	}

	// ----------FIN METODOS DE CALIFICACION REVISADO

	// ----------INICIO METODOS DE COMENTARIO
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void agregarComentarioTest() {
		Usuario usuario = entityManager.find(Usuario.class, "1");
		Producto producto = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);
		comentario.setIdComentario(8);
		comentario.setComentario("DEMASIADO MALO");

		entityManager.persist(comentario);

		Comentario comentario2 = entityManager.find(Comentario.class, 8);

		Assert.assertNotNull(comentario2);

	}

	/**
	 * Permite probar la actualizacion de un comentario cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "comentario.json", "producto.json" })
	public void actuaizarComentario() {

		Usuario usuario = entityManager.find(Usuario.class, "1");
		Producto producto = entityManager.find(Producto.class, 2);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setUsuario(usuario);
		comentario.setProducto(producto);
		comentario.setIdComentario(20);
		comentario.setComentario("MUY MALO");

		entityManager.persist(comentario);

		Comentario comentario2 = entityManager.find(Comentario.class, 20);

		Assert.assertEquals("MUY MALO", comentario2.getComentario());

		comentario2.setComentario("malo");

		entityManager.merge(comentario2);

		Assert.assertEquals("malo", comentario2.getComentario());

	}

	/**
	 * Metodo que permiter eliminar una comentario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void eliminarComentario() {

		Usuario usuario = entityManager.find(Usuario.class, "1");
		Producto producto = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setIdComentario(20);
		comentario.setComentario("Muy MALO");
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);
		entityManager.persist(comentario);

		Comentario pComentario = entityManager.find(Comentario.class, 20);
		Assert.assertNotNull(pComentario);

		entityManager.remove(pComentario);
		pComentario = entityManager.find(Comentario.class, 20);
		Assert.assertNull(pComentario);

	}

	/**
	 * Permite probar la busqueda de una calificacion por su id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "comentario.json" })
	public void buscarComentario() {
		Comentario comentario = entityManager.find(Comentario.class, 2);
		Assert.assertEquals("NO ME GUSTO MUCHO", comentario.getComentario());
	}
	// -----------FIN METODOS DE COMENTARIO REVISADO

	// INCIO DE METODO DETALLES COMPRAS

	/**
	 * Metodo insertar detalle compra
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "producto.json" })
	public void insertarDetalleCompraTest() {
		Compra comp = entityManager.find(Compra.class, 1);
		Producto product = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(comp);
		Assert.assertNotNull(product);

		DetalleCompra detalle = new DetalleCompra();
		detalle.setCompra(comp);
		detalle.setProducto(product);
		detalle.setIdDetalle(23);

		detalle.setCantidadProducto(2344);
		detalle.setPrecioVenta(32323);

		entityManager.persist(detalle);

		DetalleCompra detalles2 = entityManager.find(DetalleCompra.class, 23);

		Assert.assertNotNull(detalles2);

	}

	/**
	 * Permite probar la actualizacion de un comentario cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void actuaizarDetalleCompra() {

		Compra comp = entityManager.find(Compra.class, 1);
		Producto product = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(comp);
		Assert.assertNotNull(product);

		DetalleCompra detalle = new DetalleCompra();
		detalle.setCompra(comp);
		detalle.setProducto(product);
		detalle.setIdDetalle(23);

		detalle.setCantidadProducto(2344);
		detalle.setPrecioVenta(32323);

		entityManager.persist(detalle);

		DetalleCompra detalle2 = entityManager.find(DetalleCompra.class, 23);

		Assert.assertEquals(2344, detalle2.getCantidadProducto());

		detalle2.setCantidadProducto(300);

		entityManager.merge(detalle2);

		Assert.assertEquals(300, detalle2.getCantidadProducto());

	}

	/**
	 * Metodo que permite eliminar un detalle de compra
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void eliminarDetalleCompra() {

		Compra comp = entityManager.find(Compra.class, 1);
		Producto product = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(comp);
		Assert.assertNotNull(product);

		DetalleCompra detalle = new DetalleCompra();
		detalle.setCompra(comp);
		detalle.setProducto(product);
		detalle.setIdDetalle(23);

		detalle.setCantidadProducto(2344);
		detalle.setPrecioVenta(32323);

		entityManager.persist(detalle);

		DetalleCompra detalleEliminar = entityManager.find(DetalleCompra.class, 23);

		Assert.assertNotNull(detalleEliminar);

		entityManager.remove(detalleEliminar);

		detalleEliminar = entityManager.find(DetalleCompra.class, 23);

		Assert.assertNull(detalleEliminar);

	}

	/**
	 * Metodo que busca el detalle de una compra por el id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void buscarDetalleCompraTest() {

		DetalleCompra detalle = entityManager.find(DetalleCompra.class, 1);

		Assert.assertNotNull(detalle);

	}

	// ------------- INICION DE METODOS DE FAVORITOS
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void agregarFavoritoTest() {

		Usuario empleado = entityManager.find(Usuario.class, "1");

		Favorito favorito = new Favorito();
		Producto product = entityManager.find(Producto.class, 1);

		favorito.setProducto(product);
		favorito.setUsuario(empleado);

		favorito.setIdFavorito(5);

		entityManager.persist(favorito);

		Favorito busFavorito = entityManager.find(Favorito.class, 5);

		Assert.assertNotNull(busFavorito);

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void eliminarFavoritoTest() {

		Usuario empleado = entityManager.find(Usuario.class, "1");

		Favorito favorito = new Favorito();
		Producto product = entityManager.find(Producto.class, 1);

		favorito.setProducto(product);
		favorito.setUsuario(empleado);

		favorito.setIdFavorito(5);

		entityManager.persist(favorito);

		Favorito busFavorito = entityManager.find(Favorito.class, 5);

		Assert.assertNotNull(busFavorito);

		entityManager.remove(favorito);

		busFavorito = entityManager.find(Favorito.class, 5);

		Assert.assertNull(busFavorito);
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json","favorito.json" })
	public void actualizarFavoritoTest() {
		
		Favorito busFavorito = entityManager.find(Favorito.class, 1);
		Usuario empleado = entityManager.find(Usuario.class, "1");
		
		
		Assert.assertNotNull(empleado);
		Assert.assertNotNull(busFavorito);
		
		busFavorito.setUsuario(empleado);

		busFavorito = entityManager.find(Favorito.class, 1);
		
		Assert.assertEquals(empleado, busFavorito.getUsuario());
		
		
		
		
		
		
		
		
		
	}

}
