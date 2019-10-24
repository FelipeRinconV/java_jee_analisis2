package co.edu.uniquindio.unimarket.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.grid.entidades.*;
import co.edu.uniquindio.unimarket.excepciones.*;

@Remote
public interface adminEJBRemote {
	

	final String JNDI = "java:global/ProjectoEAR/ProjectoNegocio/AdminEJB!co.edu.uniquindio.unimarket.ejb.adminEJBRemote";

	Persona autenticarUsuario(String email, String contrasenia) ;

	List<Producto> listarProductosDisponibles();

	List<Producto> listarTodosLosProductos();
	
	List<Comentario> listarComentarios(int idProducto);

	void crearPrducto(Producto p) throws ElementoRepetidoException;

	Usuario registrarUsuario(Usuario cl) throws ElementoRepetidoException;
	
	Persona darPersonaPorCedula(String cedula);

	Persona eliminarPersona(String cedula) throws NoExisteElementosException;
	
	Producto editarProducto(Producto p);

	List<Usuario> listarUsuarios();

	Producto buscarProducto(int id) throws NoExisteElementosException;

	boolean validarCorreo(String correo);
	
	void recuperarCuenta(String correo) throws NoExisteElementosException;
}
