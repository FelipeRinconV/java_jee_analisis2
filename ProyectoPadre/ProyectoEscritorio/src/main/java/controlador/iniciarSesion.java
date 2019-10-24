package controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import co.edu.uniquindio.grid.entidades.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;
import UtilidadesAlert.Utilidades;

public class iniciarSesion {

	private ManejadorEscenarios manejadorPrincipal;

	// Ventana para pintar la escena
	private Stage Stage;

	private String clave;
	private String correo;

	@FXML
	private JFXButton btnIniciar;

	@FXML
	private JFXTextField txtContrasenia;

	@FXML
	private JFXTextField txtCorreo;

	@FXML
	private JFXButton btnRecuperar;

	@FXML
	void iniciarSesion(ActionEvent event) {
		clave = txtContrasenia.getText();

		correo = txtCorreo.getText();

		if (clave.length() > 3 && correo.length() > 5) {

			Persona admin = manejadorPrincipal.autenticarUsuario(correo, clave);

			if (admin != null) {

				manejadorPrincipal.cragarVentanaAdmin();

			} else {

				Utilidades.mostrarMensaje("Credenciales invalidades",
						"No se ha encontrado un administrados con las credenciales");

			}

		} else {

			Utilidades.mostrarMensaje("Datos  invalidades", "Por favor ingrese datos validos");

		}
	}

	//Dibuja el escenario de recuperacion de contrasenia
	@FXML
	void recuperarContrasenia(ActionEvent event) {

		try {
			manejadorPrincipal.cargarEscenaRecuperacionDeCuenta();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ManejadorEscenarios getManejadorPrincipal() {
		return manejadorPrincipal;
	}

	public void setEscenarioInicial(ManejadorEscenarios manejadorPrincipal) {
		this.manejadorPrincipal = manejadorPrincipal;
	}

	@FXML
	void initialize() {
		assert btnIniciar != null : "fx:id=\"btnIniciar\" was not injected: check your FXML file 'ventanaIniciarSesion.fxml'.";
		assert txtContrasenia != null : "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'ventanaIniciarSesion.fxml'.";
		assert txtCorreo != null : "fx:id=\"txtCorreo\" was not injected: check your FXML file 'ventanaIniciarSesion.fxml'.";
		assert btnRecuperar != null : "fx:id=\"btnRecuperar\" was not injected: check your FXML file 'ventanaIniciarSesion.fxml'.";

	}

}
