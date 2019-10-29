package co.edu.uniquindio.unimarket.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.grid.entidades.*;
import co.edu.uniquindio.unimarket.excepciones.*;

@Remote
public interface adminEJBRemote {

	final String JNDI = "java:global/ProjectoEAR/ProjectoNegocio/AdminEJB!co.edu.uniquindio.unimarket.ejb.adminEJBRemote";

	Producto darProductoPorId(int id) throws NoExisteElementosException;

	Persona autenticarUsuario(String email, String contrasenia);

	List<Producto> listarProductosDisponibles();

	List<Producto> listarTodosLosProductos();

	List<Comentario> listarComentarios(int idProducto);

	void crearPrducto(Producto p) throws ElementoRepetidoException;

	Usuario registrarUsuario(Usuario cl) throws ElementoRepetidoException;

	boolean modificarUsuario(Persona usuarioNuevo) throws NoExisteElementosException;

	Persona darPersonaPorCedula(String cedula);

	Persona eliminarPersona(String cedula) throws NoExisteElementosException;

	List<Usuario> listarUsuarios();

	List<Producto> listarProductosPorCategoria(Categoria categoria) throws NoExisteElementosException;

	Producto buscarProducto(int id) throws NoExisteElementosException;

	boolean validarCorreo(String correo);

	void recuperarCuenta(String correo) throws NoExisteElementosException;

	Double darPuntuacionProducto(int idProducto) throws NoExisteElementosException;

	void eliminarProductosPorUsuario(String cedula);

	// INICIO DE METODOS DE FUNCIONALIDAD UNICA

	boolean agregarDescuento(Descuento descuento) throws ElementoRepetidoException;

	boolean eliminarDescuento(int idDescuento);

	boolean aplicarDescuento(Descuento descuento) throws NoExisteElementosException;

	void eliminarCalificacionesAsociadas(String cedula);
	
	List<Descuento> listarDescuento();

	boolean quitarDescuento(Descuento descuento);
}
