package co.edu.uniquindio.unimarket.ejb;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.grid.entidades.Persona;

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

}
