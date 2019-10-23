package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.util.Util;

import UtilidadesAlert.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
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

	@FXML
	private JFXButton btnNuevoUsuario;

	/**
	 * REpresenta el escenario donde se agrega la vista
	 */
	private Stage escenarioListaUsuarios;

	private ManejadorEscenarios manejador;

	@FXML
	void agregarUsuario(ActionEvent event) {

		manejador.cargarEscenaAgregarUsuario();
		tablaUsuarios.refresh();

	}

	/**
	 * Metodo para añadir el boton de editar
	 */
	private void addBotonEliminar() {

		// Columna para añadir el boton a la tabla
		TableColumn<UsuarioObservable, Void> colBtn = new <UsuarioObservable, Void>TableColumn(" ");

		Callback<TableColumn<UsuarioObservable, Void>, TableCell<UsuarioObservable, Void>> cellFactory = new Callback<TableColumn<UsuarioObservable, Void>, TableCell<UsuarioObservable, Void>>() {
			@Override
			public TableCell<UsuarioObservable, Void> call(final TableColumn<UsuarioObservable, Void> param) {

				final TableCell<UsuarioObservable, Void> cell = new TableCell<UsuarioObservable, Void>() {

					// Boton que se va añadir a la tabala
					private final Button btn = new Button("Eliminar");

					{
						btn.setOnAction((ActionEvent event) -> {

							// Se obtiene los datos del indice seleccionado
							UsuarioObservable data = getTableView().getItems().get(getIndex());

							String cedula = data.getCedula().getValue();

							if (manejador.eliminarUsuario(cedula) != null) {

								manejador.eliminarDeListaObservable(data);

								Utilidades.mostrarMensaje("Operacion", "Eliminacion exitosa");
								tablaUsuarios.refresh();
							}else {
								
								Utilidades.mostrarMensaje("Operacion", "La cedula no pertenece a una persona");
							}

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		tablaUsuarios.getColumns().add(colBtn);

	}

	/**
	 * Metodo para añadir el boton de editar no se esta usando
	 */
	private void addBotonModificar() {

		// Columna para añadir el boton a la tabla
		TableColumn<UsuarioObservable, Void> colBtn = new <UsuarioObservable, Void>TableColumn(" ");

		Callback<TableColumn<UsuarioObservable, Void>, TableCell<UsuarioObservable, Void>> cellFactory = new Callback<TableColumn<UsuarioObservable, Void>, TableCell<UsuarioObservable, Void>>() {
			@Override
			public TableCell<UsuarioObservable, Void> call(final TableColumn<UsuarioObservable, Void> param) {

				final TableCell<UsuarioObservable, Void> cell = new TableCell<UsuarioObservable, Void>() {

					// Boton que se va añadir a la tabala
					private final Button btn = new Button("Modificar");

					{
						btn.setOnAction((ActionEvent event) -> {

							// Se obtiene los datos del indice seleccionado
							UsuarioObservable data = getTableView().getItems().get(getIndex());

							Utilidades.mostrarMensaje("Sirvio", "Accion de MODIFICAR");
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		tablaUsuarios.getColumns().add(colBtn);

	}

	@FXML
	void initialize() {
		columnaCorreo.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getEmail());
		columnaNombre.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getNombreCompleto());
		columnaDireccion.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getDireccion());

		addBotonEliminar();
	//	addBotonModificar();

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
