package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;

public class RecuperarCuentaController {

	
	private ManejadorEscenarios manejador;
	
	private Stage escenarioRecuperarCuenta;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnRecuperarCuenta;

    @FXML
    private JFXTextField txtCorreo;

    
    /**
     * Llama al metodo que le permite al las personas recuperar su cuenta por medio de correo electronico
     * @param event
     */
    @FXML
    void recuperarCuenta(ActionEvent event) {
    	
    	try {
			manejador.recuperarCuenta(txtCorreo.getText());
		} catch (NoExisteElementosException e) {
	
			Utilidades.mostrarMensaje("Correo incorecto", "El correo no corresponde a ninguna persona registrada");
		}

    }

    @FXML
    void initialize() {
        assert btnRecuperarCuenta != null : "fx:id=\"btnRecuperarCuenta\" was not injected: check your FXML file 'recuperarCuenta.fxml'.";
        assert txtCorreo != null : "fx:id=\"txtCorreo\" was not injected: check your FXML file 'recuperarCuenta.fxml'.";

    }

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
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

	public JFXButton getBtnRecuperarCuenta() {
		return btnRecuperarCuenta;
	}

	public void setBtnRecuperarCuenta(JFXButton btnRecuperarCuenta) {
		this.btnRecuperarCuenta = btnRecuperarCuenta;
	}

	public JFXTextField getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(JFXTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	public Stage getEscenarioRecuperarCuenta() {
		return escenarioRecuperarCuenta;
	}

	public void setEscenarioRecuperarCuenta(Stage escenarioRecuperarCuenta) {
		this.escenarioRecuperarCuenta = escenarioRecuperarCuenta;
	}
    
    
    
}