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
	
	
	 // METODOS AGREGAR ENTIDADES
	// ****************************
   
	
	// AGREGAR ADMINISTRADOR
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
	
	// AGREGAR PRODUCTO
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
    
	//AGREGAR COMPRA
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void insertarCompraTest() {

		Usuario empleado = entityManager.find(Usuario.class, "100");

		Assert.assertNotNull(empleado);

		Compra compra = new Compra();

		compra.setIdCompra(10);

		compra.setFechaCompra(new Date());

		compra.setUsuario(empleado);

		compra.setTipoPago(TipoPago.EFECTIVO);

		entityManager.persist(compra);

		Compra p = entityManager.find(Compra.class, 10);

		Assert.assertNotNull(p);

	}

	// AGREGAR USUARIO
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarUsuarioTest() {
		Usuario user = new Usuario();
		user.setCedula("345");
		user.setNombreCompleto("jose rodriguez");
		user.setNumeroTelefono("44343");
		user.setContraseña("4354");
		user.setDireccion("43654");
		user.setEmail("jose@gmail.com");
		Usuario user2 = entityManager.find(Usuario.class, user.getCedula());

		Assert.assertNull(user2);

		entityManager.persist(user);

	}

	//AGREGAR CALIFICACION
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void insertarCalificacionTest() {
		Usuario empleado = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 2);

		Assert.assertNotNull(empleado);
		Assert.assertNotNull(producto);

		Calificacion calificacion = new Calificacion();

		calificacion.setCalificacion(4);
		calificacion.setIdCalificacion(23);
		calificacion.setProducto(producto);
		calificacion.setUsuario(empleado);

		entityManager.persist(calificacion);

		Calificacion c = entityManager.find(Calificacion.class, 23);

		Assert.assertNotNull(c);

	}
	
	// AGREGAR COMENTARIO 
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void insertarComentarioTest() {
		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setIdComentario(5);
		comentario.setComentario("bueno");
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);

		entityManager.persist(comentario);

		Comentario c = entityManager.find(Comentario.class, 5);

		Assert.assertNotNull(c);

	}
	
	// AGREGAR DETALLE COMPRA
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "producto.json" })
	public void insertarDetalleCompraTest() {
		Compra compra = entityManager.find(Compra.class, 1);
		Producto producto = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(compra);
		Assert.assertNotNull(producto);

		DetalleCompra detalle = new DetalleCompra();

		detalle.setIdDetalle(5);
		detalle.setCantidadProducto(2);
		detalle.setCompra(compra);
		detalle.setProducto(producto);
		detalle.setPrecioVenta(200);
		detalle.setValorCompra(200);

		entityManager.persist(detalle);

		DetalleCompra d = entityManager.find(DetalleCompra.class, 5);

		Assert.assertNotNull(d);

	}
	
	//AGREGAR FAVORITO
	
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
	
	
	 // METODOS ELIMINAR ENTIDADES
	// ****************************
	
	/**
	 * Test para eliminar una administrador
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
	 * metodo para eliminar producto
	 */
	
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
	
	/**
	 * Test para eliminar una usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarUsuarioTest() {

		Usuario user = new Usuario();

		user.setCedula("800");

		user.setNombreCompleto("Jhon gnumeroTelefonoutierrez");

		user.setNumeroTelefono("21399");

		user.setContraseña("contra");

		user.setDireccion("Quimbaya");

		user.setEmail("admin@gmail");

		entityManager.persist(user);

		entityManager.remove(user);

		Usuario user2 = entityManager.find(Usuario.class, "800");

		Assert.assertNull(user2);

	}
	
	/**
	 * Metodo que permiter eliminar una calificacion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void eliminarCalificacion() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Calificacion calificacion = new Calificacion();

		calificacion.setCalificacion(2);
		calificacion.setIdCalificacion(6);
		calificacion.setProducto(producto);
		calificacion.setUsuario(usuario);

		entityManager.persist(calificacion);

		Calificacion pCalificacion = entityManager.find(Calificacion.class, 6);

		Assert.assertNotNull(pCalificacion);

		entityManager.remove(pCalificacion);
		pCalificacion = entityManager.find(Calificacion.class, 6);
		Assert.assertNull(pCalificacion);

	}

	/**
	 * Metodo que permiter eliminar un comentario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void eliminarComentario() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setIdComentario(5);
		comentario.setComentario("bueno");
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);
		entityManager.persist(comentario);

		Comentario pComentario = entityManager.find(Comentario.class, 5);

		Assert.assertNotNull(pComentario);

		entityManager.remove(pComentario);
		pComentario = entityManager.find(Comentario.class, 5);
		Assert.assertNull(pComentario);

	}
	
	/**
	 * Metodo que permite eliminar un detalle de compra
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void eliminarDetalleCompra() {

		Compra compra = entityManager.find(Compra.class, 2);
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(compra);
		Assert.assertNotNull(producto);

		DetalleCompra detalle = new DetalleCompra();

		detalle.setIdDetalle(10);
		detalle.setCompra(compra);
		detalle.setProducto(producto);
		detalle.setCantidadProducto(100);
		detalle.setPrecioVenta(200);

		entityManager.persist(detalle);

		DetalleCompra detalle2 = entityManager.find(DetalleCompra.class, 10);

		Assert.assertNotNull(detalle2);

		entityManager.remove(detalle2);

		detalle2 = entityManager.find(DetalleCompra.class, 10);

		Assert.assertNull(detalle2);

	}
	
	/**
	 * metodo eliminar favorito
	 */
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

	 // METODOS ACTUALIZAR ENTIDADES
	// ****************************
	
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

	/**
	 * Test para actualizar un usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void actualizarUsuarioTest() {

		Usuario user2 = entityManager.find(Usuario.class, "100");

		Assert.assertNotNull(user2);

		user2.setNombreCompleto("Nuevo");

		entityManager.merge(user2);

		Usuario user3 = entityManager.find(Usuario.class, "100");

		Assert.assertEquals("Nuevo", user3.getNombreCompleto());

	}
	
	/**
	 * Permite probar la actualizacion de una calificacion cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "comentario.json", "producto.json" })
	public void actuaizarCalificacion() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Calificacion calificacion = new Calificacion();

		calificacion.setIdCalificacion(3);

		calificacion.setCalificacion(3);

		calificacion.setUsuario(usuario);

		calificacion.setProducto(producto);

		entityManager.persist(calificacion);

		Calificacion calificacion2 = entityManager.find(Calificacion.class, 3);

		Assert.assertEquals(3, calificacion2.getCalificacion());

		calificacion2.setCalificacion(4);

		entityManager.merge(calificacion2);

		Assert.assertEquals(4, calificacion2.getCalificacion());

	}

	/**
	 * Permite probar la actualizacion de un comentario cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "comentario.json", "producto.json" })
	public void actuaizarComentario() {

		Usuario usuario = entityManager.find(Usuario.class, "100");
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(producto);

		Comentario comentario = new Comentario();

		comentario.setIdComentario(5);
		comentario.setComentario("bueno");
		comentario.setUsuario(usuario);
		comentario.setProducto(producto);
		entityManager.persist(comentario);

		Comentario comentario2 = entityManager.find(Comentario.class, 5);

		Assert.assertEquals("bueno", comentario2.getComentario());

		comentario2.setComentario("malo");

		entityManager.merge(comentario2);

		Assert.assertEquals("malo", comentario2.getComentario());

	}
	
	/**
	 * Permite probar la actualizacion de un detalle compra cambiando la url
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void actuaizarDetalleCompra() {

		Compra compra = entityManager.find(Compra.class, 2);
		Producto producto = entityManager.find(Producto.class, 3);

		Assert.assertNotNull(compra);
		Assert.assertNotNull(producto);

		DetalleCompra detalle = new DetalleCompra();

		detalle.setIdDetalle(10);
		detalle.setCompra(compra);
		detalle.setProducto(producto);
		detalle.setCantidadProducto(100);
		detalle.setPrecioVenta(200);
		entityManager.persist(detalle);

		DetalleCompra detalle2 = entityManager.find(DetalleCompra.class, 10);

		Assert.assertEquals(100, detalle2.getCantidadProducto());

		detalle2.setCantidadProducto(300);

		entityManager.merge(detalle2);

		Assert.assertEquals(300, detalle2.getCantidadProducto());

	}

	/**
	 * metodo que permite actulizar un favorito
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json", "favorito.json" })
	public void actualizarFavoritoTest() {

		Favorito busFavorito = entityManager.find(Favorito.class, 1);
		Usuario empleado = entityManager.find(Usuario.class, "100");

		Assert.assertNotNull(empleado);
		Assert.assertNotNull(busFavorito);

		busFavorito.setUsuario(empleado);

		busFavorito = entityManager.find(Favorito.class, 1);

		Assert.assertEquals(empleado, busFavorito.getUsuario());

	}
	
	 // METODOS BUSCAR ENTIDADES
	// ****************************
	
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
	
	/**
	 * permite buscar un producto
	 */
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "producto.json" })
	public void buscarProducto() {

		Producto pro = entityManager.find(Producto.class, 1);

		Assert.assertNotNull(pro);

	}
	
	/**
	 * Permite probar la busqueda de un usuario por su cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void buscarUsuario() {
		Usuario empleado = entityManager.find(Usuario.class, "100");
		Assert.assertEquals("usuario1@gmail.com", empleado.getEmail());
	}
	
	/**
	 * Permite probar la busqueda de una calificacion por su id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "calificacion.json" })
	public void buscarCalificacion() {
		Calificacion calificacion = entityManager.find(Calificacion.class, 1);
		Assert.assertEquals(1, calificacion.getCalificacion());
	}

	/**
	 * Permite probar la busqueda de una comentario por su id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "comentario.json" })
	public void buscarComentario() {
		Comentario comentario = entityManager.find(Comentario.class, 2);
		Assert.assertEquals("malo", comentario.getComentario());
	}
	
	/**
	 * Metodo que busca el detalle de una compra por el id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void buscarDetalleCompraTest() {

		DetalleCompra detalle2 = entityManager.find(DetalleCompra.class, 1);

		Assert.assertNotNull(detalle2);

	}
	
	/**
	 * Metodo que busca un favorito 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "compra.json", "detalles.json", "producto.json" })
	public void buscarFavoritoTest() {

		DetalleCompra detalle2 = entityManager.find(DetalleCompra.class, 1);

		Assert.assertNotNull(detalle2);

	}
	/**
	 * Metodo que busca una compra de un producto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void buscarCompraTest() {

		Compra compra = entityManager.find(Compra.class, 1);

		Assert.assertNotNull(compra);

	}


	
	
	

}