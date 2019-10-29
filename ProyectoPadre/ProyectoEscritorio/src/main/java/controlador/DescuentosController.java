package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Descuento;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;
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

	private Descuento descuentoSeleccionado;

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
	private Button btnActivarDescuento;

	@FXML
	private Button btnDesactivarDescuento;

	@FXML
	void activarDescuento(ActionEvent event) {

		try {

			boolean cent = manejador.aplicarDescuento(descuentoSeleccionado);

			if (cent) {
				Utilidades.mostrarMensaje("exito",
						"El descuento del: " + descuentoSeleccionado.getPorcentaje() + " %  aplicado con exito");

			} else {

				Utilidades.mostrarMensaje("precaucion",
						"El descuento del: " + descuentoSeleccionado.getPorcentaje() + " % no se puede activar " + "\n"
								+ "debido a que no hay productos en" + "\n la categoria del descuento");

				descuentoSeleccionado.setActivo(false);

			}
		} catch (NoExisteElementosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void desactivarDescuento(ActionEvent event) {

		manejador.desactivarDescuento(descuentoSeleccionado);

	}

	@FXML
	void agregarDescuencto(ActionEvent event) {

		manejador.cargarEscenaAgregarDeescuento();

	}

	@FXML
	void initialize() {

		addBotonEliminar();

		btnActivarDescuento.setDisable(true);
		btnDesactivarDescuento.setDisable(true);

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

		tablaDescuenos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> activarBotones());

	}

	/**
	 * Activamos los botones y seleccionamos el descuento asociado de la lista
	 */
	public void activarBotones() {

		if (tablaDescuenos.getItems().size() > 0) {

			Descuento seleccionado = tablaDescuenos.getSelectionModel().getSelectedItem().getDescuentoAsociado();

			btnActivarDescuento.setDisable(false);
			btnDesactivarDescuento.setDisable(false);

			this.descuentoSeleccionado = seleccionado;
		}

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

							// Se obtiene los datos del indice seleccionado
							Descuento data = getTableView().getItems().get(getIndex()).getDescuentoAsociado();

							boolean confirmacion = Utilidades.mostrarDialogo("Dialogo de confirmacion",
									"Eliminara al descuento con el id: " + data.getId(), "");

							if (confirmacion) {

								// Se quita el descuento si fue apliacado
								manejador.desactivarDescuento(data);

								manejador.eliminarDescuento(data);

								manejador.eliminarDescuentoObservable(getTableView().getItems().get(getIndex()));

								Utilidades.mostrarMensaje("Operacion", "Eliminacion exitosa");
								manejador.actualizarDescuentosObservables();
								tablaDescuenos.setItems(manejador.getDescuentosObservables());
								tablaDescuenos.refresh();

								if (tablaDescuenos.getItems().size() == 0) {

									desactivarBotones();
								}

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

		tablaDescuenos.getColumns().add(colBtn);

	}

	public Descuento getDescuentoSeleccionado() {
		return descuentoSeleccionado;
	}

	public void setDescuentoSeleccionado(Descuento descuentoSeleccionado) {
		this.descuentoSeleccionado = descuentoSeleccionado;
	}

	public void desactivarBotones() {

		btnActivarDescuento.setDisable(true);
		btnDesactivarDescuento.setDisable(true);

	}

}
