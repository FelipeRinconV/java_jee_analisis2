package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.grid.entidades.Administrador;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.ManejadorEscenarios;
import modelo.PruebaDelegado;

public class iniciarSesion implements Initializable {

	private ManejadorEscenarios manejadorPrincipal;

	String clave;
	String correo;

	@FXML
	private TextField email;

	@FXML
	private Button btnIngresar;

	@FXML
	private TextField contrasenia;

	@FXML
	private Button btnREcuperarContraseña;

	@FXML
	void recuperarContrasenia(ActionEvent event) {

	}

	@FXML
	void validarDatos(ActionEvent event) {

		String clave = contrasenia.getText();

		String correo = email.getText();

		if (clave != null && correo != null) {

			Persona admin = manejadorPrincipal.autenticarUsuario(correo, clave);

			if (admin != null) {

				/*
				 * Abrir ventana principal
				 */
				
				System.out.println("ENTRA A LA VENTANA");

			}

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

//		String clave = txtClave.getText();
//
//		String correo = email.getText();

	}

	public ManejadorEscenarios getManejadorPrincipal() {
		return manejadorPrincipal;
	}

	public void setEscenarioInicial(ManejadorEscenarios manejadorPrincipal) {
		this.manejadorPrincipal = manejadorPrincipal;
	}

}
