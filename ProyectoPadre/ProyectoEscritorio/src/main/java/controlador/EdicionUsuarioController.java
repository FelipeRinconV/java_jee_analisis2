package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.transaction.Transactional.TxType;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Rol;
import co.edu.uniquindio.grid.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;

public class EdicionUsuarioController {

	// Manejador principal de la aplicacion
	private ManejadorEscenarios manejador;

	// Ventana de la escena
	private Stage escenarioEdicion;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXButton btnAgregar;

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
	void agregarUsuario(ActionEvent event) {

		Usuario nuevoUsuario = new Usuario();

		if (txtDireccion.getText().length() > 0 && txtNombreCompleto.getText().length() > 0
				&& txtTelefono.getText().length() > 0 && txtEmail.getText().length() > 0
				&& txtCedula.getText().length() > 0 && txtContrasenia.getText().length() > 0) {

			if (manejador.validarCorreo(txtEmail.getText())) {

				nuevoUsuario.setDireccion(txtDireccion.getText());
				nuevoUsuario.setNombreCompleto(txtNombreCompleto.getText());
				nuevoUsuario.setNumeroTelefono(txtTelefono.getText());
				nuevoUsuario.setEmail(txtEmail.getText());
				nuevoUsuario.setCedula(txtCedula.getText());
				nuevoUsuario.setContrasenia(txtContrasenia.getText());

				if (cbxComprador.isSelected()) {

					nuevoUsuario.setRol(Rol.COMPRADOR);

				}

				if (cbxVendedor.isSelected()) {

					nuevoUsuario.setRol(Rol.VENDEDOR);
				}

				if (manejador.registrarUsuario(nuevoUsuario)) {

					manejador.agregarUsuarioObservable(nuevoUsuario);
					Utilidades.mostrarMensaje("Registro", "Registro exitoso !");
					escenarioEdicion.close();

				} else {

					Utilidades.mostrarMensaje("Registro", "Error en el registro");

				}

			} else {

				Utilidades.mostrarMensaje("Correo invalido", "Por favor ingrese un correo valido");
			}
		} else {

			Utilidades.mostrarMensaje("Datos", "Por favor ingrese todos los datos");
		}

	}

	// Cuando se activa el vendedor
	@FXML
	void desactivarComprador(ActionEvent event) {

		cbxComprador.setSelected(false);

	}

	// Cuando se activa el comprador
	@FXML
	void desactivarVendedor(ActionEvent event) {

		cbxVendedor.setSelected(false);

	}

	@FXML
	void initialize() {

	

		assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert txtNombreCompleto != null : "fx:id=\"txtNombreCompleto\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert txtContrasenia != null : "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert txtDireccion != null : "fx:id=\"txtDireccion\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert cbxComprador != null : "fx:id=\"cbxComprador\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert cbxVendedor != null : "fx:id=\"cbxVendedor\" was not injected: check your FXML file 'agregarUsuario.fxml'.";

	}

	/**
	 * Se carga el manejador  y los datos de la persona cuando se va a modificar
	 * @return
	 */
	public ManejadorEscenarios getManejador() {
		
	
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

	public Stage getEscenarioEdicion() {
		return escenarioEdicion;
	}

	public void setEscenarioEdicion(Stage escenarioEdicion) {
		this.escenarioEdicion = escenarioEdicion;
	}

	public ResourceBundle getResources() {
		return resources;
	}

	public void setResources(ResourceBundle resources) {
		this.resources = resources;
	}

	public URL getLocation() {
		return location;
	}

	public void setLocation(URL location) {
		this.location = location;
	}

	public JFXButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JFXButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JFXTextField getTxtNombreCompleto() {
		return txtNombreCompleto;
	}

	public void setTxtNombreCompleto(JFXTextField txtNombreCompleto) {
		this.txtNombreCompleto = txtNombreCompleto;
	}

	public JFXTextField getTxtContrasenia() {
		return txtContrasenia;
	}

	public void setTxtContrasenia(JFXTextField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}

	public JFXTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JFXTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JFXTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JFXTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JFXTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JFXTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JFXCheckBox getCbxComprador() {
		return cbxComprador;
	}

	public void setCbxComprador(JFXCheckBox cbxComprador) {
		this.cbxComprador = cbxComprador;
	}

	public JFXCheckBox getCbxVendedor() {
		return cbxVendedor;
	}

	public void setCbxVendedor(JFXCheckBox cbxVendedor) {
		this.cbxVendedor = cbxVendedor;
	}

	public JFXTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JFXTextField txtCedula) {
		this.txtCedula = txtCedula;
	}


}
