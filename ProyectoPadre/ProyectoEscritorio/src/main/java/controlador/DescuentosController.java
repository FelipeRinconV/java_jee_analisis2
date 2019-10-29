package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import UtilidadesAlert.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.DescuentoObsevable;
import modelo.ManejadorEscenarios;
import modelo.UsuarioObservable;

public class DescuentosController {

	private ManejadorEscenarios manejador;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<DescuentoObsevable> tablaDescuenos;

	@FXML
	private TableColumn<DescuentoObsevable, String> columnaId;

	@FXML
	private TableColumn<DescuentoObsevable, String> columnaActivo;

	@FXML
	private TableColumn<DescuentoObsevable, String> columnaCategoria;

	@FXML
	private TableColumn<DescuentoObsevable, String> columnaPorcentaje;

	@FXML
	private TableColumn<DescuentoObsevable, String> columnaCedula;

	@FXML
	private Button btnAgregar;

	@FXML
	private Text txtUsuarios;

	@FXML
	private Text txtProductos;

	@FXML
	private Text txtCalificaciones;

	@FXML
	void agregarDescuencto(ActionEvent event) {

		manejador.cargarEscenaAgregarDeescuento();

	}

	@FXML
	void initialize() {
		
		
		

		addBotonEliminar();
		addBotonModificar();
		
		
		columnaActivo.setCellValueFactory(descuento -> descuento.getValue().getActivo());
		columnaCategoria.setCellValueFactory(descuento -> descuento.getValue().getCategoria());
		columnaPorcentaje.setCellValueFactory(descuento -> descuento.getValue().getProcentaje());
		columnaCedula.setCellValueFactory(descuento -> descuento.getValue().getCedula_administrador());
	    columnaId.setCellValueFactory(descuento -> descuento.getValue().getIdDescuento());
		
		assert columnaId != null : "fx:id=\"columnaId\" was not injected: check your FXML file 'home.fxml'.";
		assert columnaActivo != null : "fx:id=\"columnaActivo\" was not injected: check your FXML file 'home.fxml'.";
		assert columnaCategoria != null : "fx:id=\"columnaCategoria\" was not injected: check your FXML file 'home.fxml'.";
		assert columnaPorcentaje != null : "fx:id=\"columnaPorcentaje\" was not injected: check your FXML file 'home.fxml'.";
		assert columnaCedula != null : "fx:id=\"columnaCedula\" was not injected: check your FXML file 'home.fxml'.";
		assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'home.fxml'.";
		assert txtUsuarios != null : "fx:id=\"txtUsuarios\" was not injected: check your FXML file 'home.fxml'.";
		assert txtProductos != null : "fx:id=\"txtProductos\" was not injected: check your FXML file 'home.fxml'.";
		assert txtCalificaciones != null : "fx:id=\"txtCalificaciones\" was not injected: check your FXML file 'home.fxml'.";

	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {

		tablaDescuenos.setItems(manejador.getDescuentosObservables());

		this.manejador = manejador;
	}

	/**
	 * Metodo para añadir el boton de editar
	 */
	private void addBotonEliminar() {

		// Columna para añadir el boton a la tabla
		TableColumn<DescuentoObsevable, Void> colBtn = new <DescuentoObsevable, Void>TableColumn("  Eliminar");

		Callback<TableColumn<DescuentoObsevable, Void>, TableCell<DescuentoObsevable, Void>> cellFactory = new Callback<TableColumn<DescuentoObsevable, Void>, TableCell<DescuentoObsevable, Void>>() {
			@Override
			public TableCell<DescuentoObsevable, Void> call(final TableColumn<DescuentoObsevable, Void> param) {

				final TableCell<DescuentoObsevable, Void> cell = new TableCell<DescuentoObsevable, Void>() {

					// Boton que se va añadir a la tabala
					private final Button btn = new Button("Eliminar");

					{
						btn.setOnAction((ActionEvent event) -> {
//
//							// Se obtiene los datos del indice seleccionado
//							UsuarioObservable data = getTableView().getItems().get(getIndex());
//
//							String cedula = data.getCedula().getValue();
//
//							boolean confirmacion = Utilidades.mostrarDialogo("Dialogo de confirmacion",
//									"Eliminara al usuario con cedula: " + cedula,
//									"Se Eliminaran todos los productos asociados" + "\n" + "a este usuario: ");
//
//							if (confirmacion) {
//
//								if (manejador.eliminarUsuario(cedula) != null) {
//
//									manejador.eliminarDeListaObservable(data);
//
//									Utilidades.mostrarMensaje("Operacion", "Eliminacion exitosa");
//									tablaDescuenos.refresh();
//								} else {
//
//									Utilidades.mostrarMensaje("Operacion", "La cedula no pertenece a una persona");
//								}
//
//							}
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

		tablaDescuenos.getColumns().add(colBtn);

	}

	/**
	 * Metodo para añadir el boton de editar no se esta usando
	 */
	private void addBotonModificar() {

		// Columna para añadir el boton a la tabla
		TableColumn<DescuentoObsevable, Void> colBtn = new <DescuentoObsevable, Void>TableColumn("  Eliminar");

		Callback<TableColumn<DescuentoObsevable, Void>, TableCell<DescuentoObsevable, Void>> cellFactory = new Callback<TableColumn<DescuentoObsevable, Void>, TableCell<DescuentoObsevable, Void>>() {
			@Override
			public TableCell<DescuentoObsevable, Void> call(final TableColumn<DescuentoObsevable, Void> param) {

				final TableCell<DescuentoObsevable, Void> cell = new TableCell<DescuentoObsevable, Void>() {

					// Boton que se va añadir a la tabala
					private final Button btn = new Button("Modificar");

					{
						btn.setOnAction((ActionEvent event) -> {
//
//							// Se obtiene los datos del indice seleccionado
//							UsuarioObservable data = getTableView().getItems().get(getIndex());
//
//							// Se obtiene la cedula del usuario
//							String cedula = data.getCedula().getValue();
//
//							// Se carga la escena para modificar un usuario
//							manejador.cargarScenaModificar(cedula);
//
//							// Se actualizan los usaurios de la tabla
//							manejador.actualizarUsuariosObservables();
//							// tablaDescuenos.setItems(manejador.getUsuariosObservables());

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

		tablaDescuenos.getColumns().add(colBtn);

	}

}
