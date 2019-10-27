package modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.grid.entidades.Comentario;
import co.edu.uniquindio.grid.entidades.Descuento;
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
	 * @return lista de usuarios observables
	 */
	public ObservableList<UsuarioObservable> listarUsuariosObservables() {
		List<Usuario> usuarios = listarUsuarios();
		ObservableList<UsuarioObservable> usuariosObservables = FXCollections.observableArrayList();
		for (Usuario user : usuarios) {
			usuariosObservables.add(new UsuarioObservable(user));
		}
		return usuariosObservables;
	}
	
	/**
	 * Metodo para llenar una lista observable de productos con la lista de productos que est aen la 
	 * base de datos
	 * @return
	 */
	public ObservableList<ProductoObservable> listarProductosObservables() {
		List<Producto> productos = listarTodosLosProductos();
		ObservableList<ProductoObservable> productosObservables = FXCollections.observableArrayList();
		for (Producto pro : productos) {
			productosObservables.add(new ProductoObservable(pro));
		}
		return productosObservables;
	}
	

	
	
	/**
	 * Metodo para llenar una lista observable de productos con la lista de productos que est aen la 
	 * base de datos
	 * @return
	 */
	public ObservableList<ProductoObservable> listarProductosObservablesPorCategoria(Categoria categoria) {
		
		try {
			List<Producto> productos;
			productos = listarProductosPorCategoria(categoria);
			ObservableList<ProductoObservable> productosObservables = FXCollections.observableArrayList();
			for (Producto pro : productos) {
				productosObservables.add(new ProductoObservable(pro));
			}
			return productosObservables;
		} catch (NoExisteElementosException e) {
			// TODO Auto-generated catch block
		
			 return null;
		}
		
		
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

	@Override
	public boolean validarCorreo(String correo) {
		// TODO Auto-generated method stub
		return adminEJB.validarCorreo(correo);
	}

	@Override
	public List<Producto> listarTodosLosProductos() {
		// TODO Auto-generated method stub
		return adminEJB.listarTodosLosProductos();
	}

	@Override
	public void recuperarCuenta(String correo) throws NoExisteElementosException {
		
	  adminEJB.recuperarCuenta(correo);
	}

	@Override
	public boolean modificarUsuario(Persona usuarioNuevo) throws NoExisteElementosException {
		return adminEJB.modificarUsuario(usuarioNuevo);
	}
	
	public List<Producto> listarProductosPorCategoria(Categoria categoria) throws NoExisteElementosException{
		
		return adminEJB.listarProductosPorCategoria(categoria);
	}

	/**
	 * Da la media de puntuacion de los productos
	 */
	@Override
	public Double darPuntuacionProducto(int idProducto) throws NoExisteElementosException {
		return adminEJB.darPuntuacionProducto(idProducto);
	}

	@Override
	public Producto darProductoPorId(int id) throws NoExisteElementosException {
		
		return adminEJB.darProductoPorId(id);
	}

	@Override
	public boolean agregarDescuento(Descuento descuento) throws ElementoRepetidoException {
		// TODO Auto-generated method stub
		return adminEJB.agregarDescuento(descuento);
	}

	@Override
	public boolean eliminarDescuento(int idDescuento) {
		// TODO Auto-generated method stub
		return adminEJB.eliminarDescuento(idDescuento);
	}

	@Override
	public boolean aplicarDescuento(Descuento descuento) throws NoExisteElementosException {
		// TODO Auto-generated method stub
		return adminEJB.aplicarDescuento(descuento);
	}
	/**
	 * Metodo para llenar una lista observable de desceuntos con los descuentos de la base de datos
	 * @return lista observable de descuentos 
	 */
	public ObservableList<DescuentoObsevable> listarDescuentosObservables() {
		List<Descuento> descuentos = listarDescuento();
		ObservableList<DescuentoObsevable> descuentosObservables = FXCollections.observableArrayList();
		for (Descuento desc : descuentos) {
			descuentosObservables.add(new DescuentoObsevable(desc));
		}
		return descuentosObservables;
	}

	@Override
	public List<Descuento> listarDescuento() {
		// TODO Auto-generated method stub
		return adminEJB.listarDescuento();
	}

}
