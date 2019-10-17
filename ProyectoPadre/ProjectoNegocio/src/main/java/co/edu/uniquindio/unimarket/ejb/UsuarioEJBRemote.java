package co.edu.uniquindio.unimarket.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.grid.entidades.*;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;

@Remote
public interface UsuarioEJBRemote {

	void registrarProducto(Producto p) throws ElementoRepetidoException;

	List<Producto> listarProductosPorCategoria(String categoria) throws NoExisteElementosException;

	void ComentarProducto(String comentario, Producto producto,Usuario usuario) throws ElementoRepetidoException;

	void agregarFavorito(Producto p,Usuario us);

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
