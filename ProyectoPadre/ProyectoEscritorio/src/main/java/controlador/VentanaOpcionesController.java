package controlador;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modelo.ManejadorEscenarios;

public class VentanaOpcionesController {

    @FXML
    private Button btnCrudUsuarios;

    @FXML
    private Button btnCrudProductos;

    @FXML
    private Button btnAgregarUsuario;

    
    private ManejadorEscenarios escenarioInicial;
    
    
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
		return escenarioInicial;
	}

	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
	}
    
    

}