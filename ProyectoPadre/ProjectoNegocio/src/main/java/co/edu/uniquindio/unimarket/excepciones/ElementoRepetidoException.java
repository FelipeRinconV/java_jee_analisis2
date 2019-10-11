package co.edu.uniquindio.unimarket.excepciones;

public class ElementoRepetidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Excepcion para validar el ingreso de los productos
	 */

	public ElementoRepetidoException(String mensaje) {

		super(mensaje);

	}

}
