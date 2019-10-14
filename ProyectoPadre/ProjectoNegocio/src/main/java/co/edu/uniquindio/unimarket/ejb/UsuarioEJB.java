package co.edu.uniquindio.unimarket.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.uniquindio.grid.entidades.Compra;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;

/**
 * Session Bean implementation class UsuarioEJB
 */
@Stateless
@LocalBean
public class UsuarioEJB implements UsuarioEJBRemote {

	@PersistenceContext
	EntityManager entytiManager;

	/**
	 * Default constructor.
	 */
	public UsuarioEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que le permite a la persona registrar un producto
	 * preguntar como hace para guardar la relacion con persona
	 * ya que el producto necesita una persona
	 */
	@Override
	public void registrarProducto(Producto p) throws ElementoRepetidoException {

		// TODO Auto-generated method stub

		if (entytiManager.find(Producto.class, p.getIdProducto()) != null) {

			throw new ElementoRepetidoException("el producto ya existe");
		}

		entytiManager.persist(p);

	}

	/**
	 *Metodo que le permite al usuario listar los productos EN GENERAL por una categoria en especifico
	 *@return LISTA de PRODUCTOS
	 */
	@Override
	public List<Producto> listarProductosPorCategoria(String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que le permite al usuario realizar el comentario de un producto
	 * PREGUNTAR COMO REALIZARLO
	 */
	@Override
	public void ComentarProducto(String comentario, Producto p) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo que le permite al usuario agregar un producto a sus favoritos
	 */
	@Override
	public void agregarFavorito(Producto p) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo que le permite al usuario eliminar un producto de su lista de favoritos
	 */
	@Override
	public void eliminarFavorito(Producto p) {
		// TODO Auto-generated method stub

	}

	/**
	 * le permite al usuario agregar el producto al carrito 
	 * PREGUNTAR AL PROFESOR COMO REALIZARLO 
	 */
	@Override
	public void añadirProductoCarrito(Producto p) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo que le permite al usuario comprar un producto
	 */
	@Override
	public void comprarProducto(Producto p) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo que le permite al usuario calificar un producto de 1-5 extrellas
	 * PREGUNTAR AL USUARIO COMO VALIDAR ESTA PUNTUACION
	 */
	@Override
	public void calificarProducto(Producto p, int calificacion) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo que le permite al usuario listar los productos por criterios especificos 
	 * PREGUNTAR AL PROFESOR COMO REALIZARLO SOBRE LOS CRITERIOS EN LAS CONSULTAS
	 */
	@Override
	public List<Producto> listarProductosPorCriterios(String criterios) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Metodo que le permite al usuario listar las compras QUE AH REALIZADO
	 */
	@Override
	public List<Compra> listarCompras() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que le permite al usuario LISTAR LOS PRODUCTO QUE HAYA REGISTRADO
	 */
	@Override
	public List<Producto> listarSusProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que le permite al usaurio eliminar un producto que haya registrado previamente 
	 * PREGUNTAR AL PROFESOR COMO VALIDAR QUE EL PRODUCTO PERTENECE AL USUARIO
	 */
	@Override
	public void eliminarProducto(Producto p) {
		// TODO Auto-generated method stub

	}

	/**
	 * ELimina varios productos de los que el usuario tenga registrados
	 */
	@Override
	public void eliminarListaProducto(List<Producto> productos) {
		// TODO Auto-generated method stub

	}

}
