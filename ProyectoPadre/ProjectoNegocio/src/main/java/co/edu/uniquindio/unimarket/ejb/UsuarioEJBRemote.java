package co.edu.uniquindio.unimarket.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.grid.entidades.*;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;

@Remote
public interface UsuarioEJBRemote {

	void registrarProducto(Producto p) throws ElementoRepetidoException;

	List<Producto> listarProductosPorCategoria(String categoria);

	void ComentarProducto(String comentario, Producto p);

	void agregarFavorito(Producto p);

	void eliminarFavorito(Producto p);

	void añadirProductoCarrito(Producto p);

	void comprarProducto(Producto p);

	void calificarProducto(Producto p, int calificacion);

	List<Producto> listarProductosPorCriterios(String criterios);

	List<Compra> listarCompras();

	List<Producto> listarSusProductos();

	void eliminarProducto(Producto p);

	void eliminarListaProducto(List<Producto> productos);

}
