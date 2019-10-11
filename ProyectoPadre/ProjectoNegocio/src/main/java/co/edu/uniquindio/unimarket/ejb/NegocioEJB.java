package co.edu.uniquindio.unimarket.ejb;


import java.awt.Window.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.grid.entidades.Comentario;
import co.edu.uniquindio.grid.entidades.Persona;

import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.excepciones.*;


/**
 * Session Bean implementation class NegocioEJB
 */
@Stateless
@LocalBean
public class NegocioEJB implements NegocioEJBRemote {

	@PersistenceContext
	private EntityManager entytiManager;

	/**
	 * Default constructor.
	 */
	public NegocioEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que eprmite autenticar un usuario
	 */
	@Override
	public Persona autenticarUsuario(String email, String contraseña) {

		TypedQuery<Persona> q = entytiManager.createNamedQuery(Persona.BUSCAR_PERSONA, Persona.class);

		q.setParameter("email", email);

		q.setParameter("contra", contraseña);

		Persona persona = q.getSingleResult();

		if (persona == null) {
			return null;
		} else {

			return persona;
		}

	}

	/**
	 * Metodo que da los productos disponibles segun cantidad y fecha
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

}
