package controlador;

import java.awt.Button;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;
import modelo.UsuarioObservable;

public class UsuarioController {
	/**
	 * instancia del manejador de escenario
	 */

	private ManejadorEscenarios escenarioInicial;

	private UsuarioObservable usuarioObservable;

	public ManejadorEscenarios getEscenarioInicial() {
		return escenarioInicial;
	}

	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
	}

	public UsuarioObservable getUsuarioObservable() {
		return usuarioObservable;
	}

	public void setUsuarioObservable(UsuarioObservable usuarioObservable) {
		this.usuarioObservable = usuarioObservable;
	}

}
