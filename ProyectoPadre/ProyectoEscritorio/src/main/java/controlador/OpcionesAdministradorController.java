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
    private URL location;

    @FXML
    private JFXButton btnUsuarios;

    @FXML
    private Button productos;

    @FXML
    void abrirEscenaProductos(ActionEvent event) {
    	
    	
    	Utilidades.mostrarMensaje("SIRVIO !", "Se va abrir la scena de PRODUCTOS");
    	
    }

    @FXML
    void abrirEscenaUsuarios(ActionEvent event) {
    	
    	Utilidades.mostrarMensaje("SIRVIO !", "Se va abrir la scena de USUARIOS");

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