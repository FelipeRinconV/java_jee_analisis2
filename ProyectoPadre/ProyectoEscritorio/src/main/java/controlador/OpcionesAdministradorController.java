package controlador;

import com.jfoenix.controls.JFXButton;

import UtilidadesAlert.Utilidades;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modelo.ManejadorEscenarios;

public class OpcionesAdministradorController {

	private ManejadorEscenarios manejador;

	@FXML
	private ResourceBundle resources;


    @FXML
    private JFXButton btnPricnipal;

	
	@FXML
	private URL location;

	@FXML
	private JFXButton btnUsuarios;

	@FXML
	private Button productos;

	@FXML
	void cargarPrincipal(ActionEvent event) {
		
		manejador.cargarHomeDescuetos();

	}

	/*
	 * Metodo para cargar las escena de productos a la ventana principal del admin
	 */
	@FXML
	void abrirEscenaProductos(ActionEvent event) {

		manejador.cargarEscenarioProductos();

	}

	/**
	 * Metodo para cargar la escena de listar usuario a la ventana principal del
	 * admin
	 * 
	 * @param event
	 */
	@FXML
	void abrirEscenaUsuarios(ActionEvent event) {

		manejador.cargarEscenarioUsuarios();

	}

	@FXML
	void initialize() {
		assert btnUsuarios != null : "fx:id=\"btnUsuarios\" was not injected: check your FXML file 'prueba.fxml'.";
		assert productos != null : "fx:id=\"productos\" was not injected: check your FXML file 'prueba.fxml'.";

	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

}