package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;
import modelo.UsuarioObservable;

public class ListaUsuariosController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<UsuarioObservable> tablaUsuarios;

	@FXML
	private TableColumn<UsuarioObservable, String> columnaNombre;

	@FXML
	private TableColumn<UsuarioObservable, String> columnaCorreo;

	@FXML
	private TableColumn<UsuarioObservable, String> columnaTipo;

	@FXML
	private TableColumn<UsuarioObservable, String> columnaEmail;

	@FXML
	private TableColumn<UsuarioObservable, String> columnaDireccion;

	/**
	 * REpresenta el escenario donde se agrega la vista
	 */
	private Stage escenarioListaUsuarios;

	private ManejadorEscenarios manejador;

	@FXML
	void initialize() {
		columnaCorreo.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getEmail());
		columnaNombre.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getNombreCompleto());

		// cuando se seleccione un usuario se ejecuta el metodo
//		tablaUsuarios.getSelectionModel().selectedItemProperty()
//				.addListener((observable, oldValue, newValue) -> mostrarDetalleUsuario(newValue));

	}

	public TableView<UsuarioObservable> getTablaUsuarios() {
		return tablaUsuarios;
	}

	public void setTablaUsuarios(TableView<UsuarioObservable> tablaUsuarios) {
		this.tablaUsuarios = tablaUsuarios;
	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;

		tablaUsuarios.setItems(manejador.getUsuariosObservables());
	}

	public Stage getEscenarioListaUsuarios() {
		return escenarioListaUsuarios;
	}

	public void setEscenarioListaUsuarios(Stage escenarioListaUsuarios) {
		this.escenarioListaUsuarios = escenarioListaUsuarios;

	}

}
