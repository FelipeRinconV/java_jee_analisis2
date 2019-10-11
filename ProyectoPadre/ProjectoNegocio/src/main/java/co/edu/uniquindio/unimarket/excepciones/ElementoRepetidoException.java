package co.edu.uniquindio.unimarket.excepciones;

public class ElementoRepetidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Excepcion para validar el ingreso de elementos repetidos
	 */

	public ElementoRepetidoException(String mensaje) {

		super(mensaje);

	}

}
