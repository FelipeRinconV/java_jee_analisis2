package controlador;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.ManejadorEscenarios;
import modelo.ProductoObservable;

public class ListaProdcutosController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<ProductoObservable> tablaProductos;

	@FXML
	private TableColumn<ProductoObservable, String> clumnaCedula;

	@FXML
	private TableColumn<ProductoObservable, String> columnaNombre;

	@FXML
	private TableColumn<ProductoObservable, String> columnaId;

	@FXML
	private TableColumn<ProductoObservable, String> columnaPrecio;

	@FXML
	private TableColumn<ProductoObservable, String> columnaCategoria;

	@FXML
	private JFXButton btnDetalles;

	private ManejadorEscenarios manejador;

	@FXML
	void verDetalleProducto(ActionEvent event) {

		// Abrir la ventana de detalles de productos

	}

	@FXML
	void initialize() {

		clumnaCedula.setCellValueFactory(productoCelda -> productoCelda.getValue().getCedulaUsuario());
		columnaNombre.setCellValueFactory(productoCelda -> productoCelda.getValue().getNombre());
		columnaId.setCellValueFactory(productoCelda -> productoCelda.getValue().getId());
		columnaCategoria.setCellValueFactory(productoCelda -> productoCelda.getValue().getCategoria());
		columnaPrecio.setCellValueFactory(productoCelda -> productoCelda.getValue().getPrecio());

		tablaProductos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> activarDetallesProducto(newValue));

	}

	/**
	 * Se activa cuando se selecciona un producto y se abre la ventana de detalle del producto
	 * 
	 * @param productoObservable
	 */
	private void activarDetallesProducto(ProductoObservable productoObservable) {

		btnDetalles.setDisable(false);
		
		manejador.cargarEscenaDetallesProductos(productoObservable);

	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		
		
		this.manejador = manejador;
		
		tablaProductos.setItems(manejador.getProductosObservables());
	}

}