package co.edu.uniquindio.unimarket.excepciones;

public class NoExisteElementosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Excepcion para valdiar cuando no existe ningun registro con los criterios
	 */

	public NoExisteElementosException(String mensaje) {

		super(mensaje);

	}

}
