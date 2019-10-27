package UtilidadesAlert;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * Permite mostrar diferentes alertas para informacion y confirmacion de acciones
 * 
 * @version 1.0
 */
public final class Utilidades {

	/**
	 * permite mostrar un texto informativo en pantalla
	 * 
	 * @param titulo  subtitulo de la alerta
	 * @param mensaje mensaje principal
	 */
	public static void mostrarMensaje(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Mensaje");
		alert.setHeaderText(titulo);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	/**
	 * 
	 * @param titulo de la ventana de la alerta
	 * @param cabecera mensaje principal ed la alerta
	 * @param mensaje mensaje al usuario
	 * @return true si confirma falso en el caso contrario
	 */
	public static boolean mostrarDialogo(String titulo,String cabecera, String mensaje) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecera);
		alert.setContentText(mensaje);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			return true;
		} else {

			return false;
		}

	}

	public static boolean mostrarDialogo(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setContentText(mensaje);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			return true;
		} else {

			return false;
		}

	}


}
