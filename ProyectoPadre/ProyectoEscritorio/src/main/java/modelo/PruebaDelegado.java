package modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.edu.uniquindio.grid.entidades.Comentario;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;
import co.edu.uniquindio.unimarket.ejb.adminEJBRemote;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PruebaDelegado implements adminEJBRemote {

	private adminEJBRemote adminEJB;

	// Singleton
	public static PruebaDelegado pruebaDelegado = instancia();

	private static PruebaDelegado instancia() {

		if (pruebaDelegado == null) {

			pruebaDelegado = new PruebaDelegado();

			return pruebaDelegado;

		} else {

			return pruebaDelegado;

		}

	}

	private PruebaDelegado() {

		try {
			adminEJB = (adminEJBRemote) new InitialContext().lookup(adminEJBRemote.JNDI);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Persona autenticarUsuario(String email, String contrasenia) {
		return adminEJB.autenticarUsuario(email, contrasenia);
	}

	public Usuario registrarUsuario(Usuario cl) throws ElementoRepetidoException {
		return adminEJB.registrarUsuario(cl);
	}

	public int hashCode() {
		return adminEJB.hashCode();
	}

	public List<Usuario> listarUsuarios() {
		return adminEJB.listarUsuarios();
	}

	public Producto buscarProducto(int id) throws NoExisteElementosException {
		return adminEJB.buscarProducto(id);
	}

	public boolean equals(Object obj) {
		return adminEJB.equals(obj);
	}

	public Producto editarProducto(Producto p) {
		return adminEJB.editarProducto(p);
	}

	public List<Producto> listarProductosDisponibles() {
		return adminEJB.listarProductosDisponibles();
	}

	public List<Comentario> listarComentarios(int idProducto) {
		return adminEJB.listarComentarios(idProducto);
	}

	public void crearPrducto(Producto p) throws ElementoRepetidoException {
		adminEJB.crearPrducto(p);
	}

	public String toString() {
		return adminEJB.toString();
	}

	/**
	 * Metodo para devolver la lista de usuarios en una lista de usuarios
	 * observables
	 * 
	 * @return
	 */
	public ObservableList<UsuarioObservable> listarUsuariosObservables() {
		List<Usuario> usuarios = listarUsuarios();
		ObservableList<UsuarioObservable> empleadosObservables = FXCollections.observableArrayList();
		for (Usuario user : usuarios) {
			empleadosObservables.add(new UsuarioObservable(user));
		}
		return empleadosObservables;
	}

	@Override
	public Persona darPersonaPorCedula(String cedula) {
		// TODO Auto-generated method stub
		return adminEJB.darPersonaPorCedula(cedula);
	}

	/**
	 * Metodo para eliminar una persona dada su cedula
	 */
	@Override
	public Persona eliminarPersona(String cedula) throws NoExisteElementosException {

		return adminEJB.eliminarPersona(cedula);
	}

}
