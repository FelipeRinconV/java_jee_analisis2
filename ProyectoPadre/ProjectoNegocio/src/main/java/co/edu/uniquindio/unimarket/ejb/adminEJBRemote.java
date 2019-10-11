package co.edu.uniquindio.unimarket.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.grid.entidades.*;
import co.edu.uniquindio.unimarket.excepciones.*;

@Remote
public interface adminEJBRemote {

	Persona autenticarUsuario(String email, String contraseña);

	List<Producto> listarProductosDisponibles();

	List<Comentario> listarComentarios(int idProducto);

	void crearPrducto(Producto p) throws ElementoRepetidoException;

	Usuario registrarUsuario(Usuario cl) throws ElementoRepetidoException;

	Producto editarProducto(Producto p);
	
	List<Usuario> listarUsuarios() throws NoExisteElementosException;

	Producto buscarProducto(int id) throws NoExisteElementosException;
}
