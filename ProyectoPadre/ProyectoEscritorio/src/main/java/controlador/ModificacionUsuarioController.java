package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Rol;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;

public class ModificacionUsuarioController {

	private ManejadorEscenarios manejador;

	private Stage stage;

	private Persona personaModificable;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXButton btnModificar;

	@FXML
	private JFXTextField txtNombreCompleto;

	@FXML
	private JFXTextField txtContrasenia;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtDireccion;

	@FXML
	private JFXTextField txtCedula;

	@FXML
	private JFXTextField txtTelefono;

	@FXML
	private JFXCheckBox cbxComprador;

	@FXML
	private JFXCheckBox cbxVendedor;

	@FXML
	void desactivarComprador(ActionEvent event) {

	}

	@FXML
	void desactivarVendedor(ActionEvent event) {

	}

	@FXML
	void modificarUsuario(ActionEvent event) {

		if (personaModificable != null) {

			if (txtDireccion.getText().length() > 0 && txtNombreCompleto.getText().length() > 0
					&& txtTelefono.getText().length() > 0 && txtEmail.getText().length() > 0
					&& txtCedula.getText().length() > 0 && txtContrasenia.getText().length() > 0) {

				if (manejador.validarCorreo(txtEmail.getText())) {

					personaModificable.setDireccion(txtDireccion.getText());
					personaModificable.setNombreCompleto(txtNombreCompleto.getText());
					personaModificable.setNumeroTelefono(txtTelefono.getText());
					personaModificable.setEmail(txtEmail.getText());
					personaModificable.setCedula(txtCedula.getText());
					personaModificable.setContrasenia(txtContrasenia.getText());

					try {
						if (manejador.modificarUsuario(personaModificable)) {

							manejador.actualizarUsuariosObservables();
							Utilidades.mostrarMensaje("Modificacion", "MOdificacion exitosa!");

							stage.close();

						} else {

							Utilidades.mostrarMensaje("Problema", "Modificacion fallida");

						}
					} catch (NoExisteElementosException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					Utilidades.mostrarMensaje("Correo invalido", "Por favor ingrese un correo valido");
				}
			} else {

				Utilidades.mostrarMensaje("Datos", "Por favor ingrese todos los datos");
			}

		}

	}

	@FXML
	void initialize() {
		assert btnModificar != null : "fx:id=\"btnModificar\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert txtNombreCompleto != null : "fx:id=\"txtNombreCompleto\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert txtContrasenia != null : "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert txtDireccion != null : "fx:id=\"txtDireccion\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert txtCedula != null : "fx:id=\"txtCedula\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert cbxComprador != null : "fx:id=\"cbxComprador\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";
		assert cbxVendedor != null : "fx:id=\"cbxVendedor\" was not injected: check your FXML file 'modificacionUsuario.fxml'.";

	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {

		this.manejador = manejador;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Persona getPersonaModificable() {

		return personaModificable;
	}

	public void setPersonaModificable(Persona personaModificable) {
		if (personaModificable != null) {

			txtEmail.setText(personaModificable.getEmail());
			txtCedula.setText(personaModificable.getCedula());
			txtCedula.setDisable(true);
			txtContrasenia.setText(personaModificable.getContraseña());
			txtDireccion.setText(personaModificable.getDireccion());
			txtNombreCompleto.setText(personaModificable.getNombreCompleto());
			txtTelefono.setText(personaModificable.getNumeroTelefono());

		}

		this.personaModificable = personaModificable;
	}

}
