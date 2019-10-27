package co.edu.uniquindio.unimarket.ejb;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder.In;

import co.edu.uniquindio.grid.entidades.*;
import co.edu.uniquindio.unimarket.excepciones.*;

/**
 * Session Bean implementation class NegocioEJB
 */
@Stateless
@LocalBean
public class AdminEJB implements adminEJBRemote {

	@PersistenceContext
	private EntityManager entytiManager;

	/**
	 * Default constructor.
	 */
	public AdminEJB() {
		// TODO Auto-generated constructor stub
	}

	// METODOS DE EL ADMIN
	/**
	 * Metodo que eprmite autenticar un usuario
	 */
	@Override
	public Persona autenticarUsuario(String email, String contrasenia) {

		TypedQuery<Persona> q = entytiManager.createNamedQuery(Persona.BUSCAR_PERSONA_POR_EMAIL_Y_CONTRASENIA,
				Persona.class);

		q.setParameter("email", email);

		q.setParameter("contra", contrasenia);

		List<Persona> lista = q.getResultList();

		if (lista.isEmpty()) {
			return null;
		}

		Persona persona = lista.get(0);
		return persona;

	}

	/**
	 * Metodo para registrar el usuario
	 * 
	 * @throws ElementoRepetidoException
	 */
	public Usuario registrarUsuario(Usuario cl) throws ElementoRepetidoException {
		// throws CedulaRepetidoException, EmailRepetidoException {

		// Buscamos si la cedula no esta repetida
		if (entytiManager.find(Usuario.class, cl.getCedula()) != null) {

			throw new ElementoRepetidoException("Ya existe un usuario  con esta cedula");
		}

		// Buscamos por el emails true si es validado
		if (buscarUsuarioPorEmail(cl.getEmail()) != null) {

			throw new ElementoRepetidoException("Ya existe un usuario registrado con este	email");

		}

		try {

			entytiManager.persist(cl);

			return cl;

		} catch (Exception e) {

			return null;
		}

	}

	/**
	 * Listar todos los usuarios
	 * 
	 * @return lista de los usuarios registrados
	 */
	public List<Usuario> listarUsuarios() {

		TypedQuery<Usuario> query = entytiManager.createNamedQuery(Usuario.LISTAR_USUARIOS, Usuario.class);

		List<Usuario> listaUsuarios = query.getResultList();

		return listaUsuarios;

	}

	/**
	 * Metodo que devuelve un producto dado su id
	 */
	public Producto buscarProducto(int id) throws NoExisteElementosException {

		TypedQuery<Producto> q = entytiManager.createNamedQuery(Producto.PRODUCTO_POR_ID, Producto.class);

		q.setParameter("id", id);
		List<Producto> pEncontrado = q.getResultList();

		if (pEncontrado == null) {

			throw new NoExisteElementosException("No existe el producto con el ID indicado");
		}

		return pEncontrado.get(0);

	}

	/**
	 * permite buscar una personas usando su email
	 * 
	 * @param email email de la presona
	 * @return persona con el email especificado
	 */
	private Usuario buscarUsuarioPorEmail(String email) {

		try {
			TypedQuery<Usuario> query = entytiManager.createNamedQuery(Usuario.BUSCAR_POR_EMAIL, Usuario.class);
			query.setParameter("email", email);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public Producto editarProducto(Producto p) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que da los productos disponibles segun cantidad y fecha
	 * 
	 * @return devuelve una lista con lso productos no vencidos y con cantidad mayor
	 *         a 0
	 */
	@Override
	public List<Producto> listarProductosDisponibles() {

		TypedQuery<Producto> query = entytiManager.createNamedQuery(Producto.PRODUCTOS_DISPONIBLES, Producto.class);

		query.setParameter("fechaActual", new Date());

		return query.getResultList();

	}

	@Override
	public List<Comentario> listarComentarios(int idProducto) {

		TypedQuery<Comentario> query = entytiManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS_PRODUCTO,
				Comentario.class);

		query.setParameter("id", idProducto);

		return query.getResultList();

	}

	/**
	 * Metodo apra crear productos
	 */
	@Override
	public void crearPrducto(Producto p) throws ElementoRepetidoException {
		// TODO Auto-generated method stub

		if (entytiManager.find(Producto.class, p.getIdProducto()) != null) {

			throw new ElementoRepetidoException("el producto ya existe");
		}

		entytiManager.persist(p);

	}

	/**
	 * Metodo que da un usuario dada su cedula
	 */
	@Override
	public Persona darPersonaPorCedula(String cedula) {

		if (cedula.length() > 1) {

			return entytiManager.find(Persona.class, cedula);

		}

		return null;
	}

	/**
	 * Metodo para eliminar el usuario
	 */
	@Override
	public Persona eliminarPersona(String cedula) throws NoExisteElementosException {

		Persona p = entytiManager.find(Persona.class, cedula);

		if (p != null) {

			try {
				entytiManager.remove(p);

				return p;

			} catch (Exception e) {

				e.printStackTrace();
				return null;
			}

		} else {

			throw new NoExisteElementosException("No existe una persona con la cedula indicada");
		}

	}

	/**
	 * Metodo que permite validar correos gmail
	 */
	@Override
	public boolean validarCorreo(String correo) {

		boolean valido = false;

		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mEmail = pattern.matcher(correo.toLowerCase());
		if (mEmail.find() == true) {
			valido = true;
		}
		return valido;
	}

	/**
	 * Lista todos los productos sin importar su cantidad o fecha
	 */
	@Override
	public List<Producto> listarTodosLosProductos() {

		TypedQuery<Producto> query = entytiManager.createNamedQuery(Producto.LISTAR_TODOS_LOS_PRODUCTOS,
				Producto.class);

		return query.getResultList();
	}

	public List<Producto> listarProductosPorCategoria(Categoria categoria) throws NoExisteElementosException {

		TypedQuery<Producto> query = entytiManager.createNamedQuery(Producto.PRODUCTOS_POR_CATEGORIA, Producto.class);

		query.setParameter("tipo", categoria);

		List<Producto> pro = query.getResultList();

		if (!pro.isEmpty()) {

			return pro;

		} else {

			throw new NoExisteElementosException("No hay productos registrados en esta categria");
		}

	}

	/**
	 * Metodo que envia un correo apra la recuperacion de la cuenta de una persona
	 */
	@Override
	public void recuperarCuenta(String correo) throws NoExisteElementosException {

		TypedQuery<Persona> q = entytiManager.createNamedQuery(Persona.BUSCAR_POR_EMAIL, Persona.class);

		q.setParameter("email", correo);

		Persona persona = q.getSingleResult();

		if (persona != null) {

			// Correo de donde sale el mensaje
			final String username = "unimarketrecuperacion@gmail.com";

			// Contraseña de donde sale el mensaje
			final String password = "analisis2";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {

				// Define message
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				// Asunto del correo
				message.setSubject("RECUPERACION DE CUENTA");

				// Se agrega el destinatario
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(persona.getEmail()));

				// Se agrega el mensaje del correo
				message.setText("Estimado usuario sus credenciales son las siguientes: " +

						"Correo: " + persona.getEmail() + "| Contraseña: " + persona.getContraseña());

				// Envia el mensaje
				Transport.send(message);
			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {

			throw new NoExisteElementosException("El correo no pertenece a una persona registrada");
		}

	}

	/**
	 * Metodo para modificar un usuario
	 */
	@Override
	public boolean modificarUsuario(Persona usuarioNuevo) throws NoExisteElementosException {

		Persona user = entytiManager.find(Persona.class, usuarioNuevo.getCedula());

		if (user != null) {

			try {

				user.setEmail(usuarioNuevo.getEmail());

				user.setDireccion(usuarioNuevo.getDireccion());

				user.setNumeroTelefono(usuarioNuevo.getNumeroTelefono());

				user.setNombreCompleto(usuarioNuevo.getNombreCompleto());

				user.setDireccion(usuarioNuevo.getDireccion());

				entytiManager.merge(user);

				// Intento sin cedula
				// user.setCedula(usuarioNuevo.getCedula());

				return true;

			} catch (Exception e) {

				e.printStackTrace();

			}

		} else {

			new NoExisteElementosException("No existe ningun usuario con la cedula indicada");

		}

		return false;
	}

	/**
	 * 
	 * @param idProducto
	 * @return
	 */
	public Double darPuntuacionProducto(int idProducto) throws NoExisteElementosException {

		Producto producto = entytiManager.find(Producto.class, idProducto);

		if (producto != null) {

			TypedQuery<Double> query = entytiManager.createNamedQuery(Calificacion.MEDIA_DE_CALIFICACION, Double.class);

			query.setParameter("id", idProducto);

			Double puntuacion = query.getSingleResult();

			return puntuacion;
		} else {

			throw new NoExisteElementosException("No hay productos registrados con este id");

		}

	}

	@Override
	public Producto darProductoPorId(int id) throws NoExisteElementosException {

		Producto p = entytiManager.find(Producto.class, id);

		if (p != null) {

			return p;
		} else {

			throw new NoExisteElementosException("El id no corresponde a un producto registrado");
		}

	}

	public void elimnarAsociaciones(Usuario user) {

	}

	// ------------ FUNCIONALIDAD UNICA
	@Override
	public boolean agregarDescuento(Descuento descuento) throws ElementoRepetidoException {

		try {
			entytiManager.persist(descuento);
			return true;
		} catch (Exception e) {
			// TODO: dle exception
			return false;
		}

	}

	@Override
	public boolean eliminarDescuento(int idDescuento) {

		Descuento d = entytiManager.find(Descuento.class, idDescuento);

		if (d != null) {

			entytiManager.remove(d);
			return true;

		} else {

			return false;

		}

	}

	@Override
	public boolean aplicarDescuento(Descuento descuento) throws NoExisteElementosException {

		TypedQuery<Producto> query = entytiManager.createNamedQuery(Producto.PRODUCTOS_POR_CATEGORIA, Producto.class);

		query.setParameter("tipo", descuento.getCategoria());

		List<Producto> productos = query.getResultList();

		if (productos.isEmpty()) {

			throw new NoExisteElementosException("No hay productos por la categoria aplicada");

		} else {

			for (Producto p : productos) {

				p = entytiManager.find(Producto.class, p.getIdProducto());

				double procentaje = descuento.getPorcentaje() / 100;

				// ACTUALIZAMOS LOS PRECIOS DE LOS PRODUCTOS DE LA CATEGORIA CORRESPONDIENTE
				p.setPrecio(p.getPrecio() - (p.getPrecio() * (procentaje)));

				entytiManager.merge(p);

			}

			return true;

		}

	}

	/**
	 *Metodo para devolver una lista con TODOS los descuentos que se encuentrene en la base de datos
	 */
	@Override
	public List<Descuento> listarDescuento() {

		TypedQuery<Descuento> query = entytiManager.createNamedQuery(Descuento.LISTAR_DESCUENTOS, Descuento.class);

		List<Descuento> descuentos = query.getResultList();

		return descuentos;

	}

}
