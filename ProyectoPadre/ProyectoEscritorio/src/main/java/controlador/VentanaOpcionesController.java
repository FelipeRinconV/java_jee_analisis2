package controlador;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;

public class VentanaOpcionesController {

    @FXML
    private Button btnCrudUsuarios;

    @FXML
    private Button btnCrudProductos;

    @FXML
    private Button btnAgregarUsuario;

    /**
     * REpresenta el escenario donde se agrega la vista
     */
    private Stage escenarioOpciones;
    
    private ManejadorEscenarios manejador;
    
    
    @FXML
    void abrirAgregarUsuario(ActionEvent event) {

    }

    @FXML
    void abrirCrudProductos(ActionEvent event) {

    }

    @FXML
    void abrirCrudUsuarios(ActionEvent event) {

    }

	public ManejadorEscenarios getEscenarioInicial() {
		return manejador;
	}


	public Stage getEscenarioOpciones() {
		return escenarioOpciones;
	}

	public void setEscenarioOpciones(Stage escenarioOpciones) {
		this.escenarioOpciones = escenarioOpciones;
	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}
    
    

}