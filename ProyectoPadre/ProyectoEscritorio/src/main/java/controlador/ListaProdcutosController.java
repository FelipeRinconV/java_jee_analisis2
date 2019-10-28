package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.sun.mail.imap.protocol.MODSEQ;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	private ProductoObservable producto;

	@FXML
	private JFXButton btnFiltrar;

	@FXML
	private JFXComboBox<String> cbxCategoria;

	/**
	 * Metodo que se llama cuando se filtran los productos
	 * 
	 * @param event
	 */
	
	

    @FXML
    void cbxCategoriaBuscar(ActionEvent event) {

    }
	
	
	@FXML
	void filtrarProductos(ActionEvent event) {

		
		int index = cbxCategoria.getSelectionModel().getSelectedIndex();

		if (index >= 0) {

			String seleccion = cbxCategoria.getItems().get(index);

			switch (seleccion) {
			case "DEPORTE":

				auxliarFiltrarPorCategoria(Categoria.DEPORTE);

				break;

			case "TECNOLOGIA":

				auxliarFiltrarPorCategoria(Categoria.TECNOLOGIA);

				break;
			case "MODA":

				auxliarFiltrarPorCategoria(Categoria.MODA);

				break;

			case "LIBROS":

				auxliarFiltrarPorCategoria(Categoria.LIBROS);

				break;
			case "JOYAS":

				auxliarFiltrarPorCategoria(Categoria.JOYAS);

				break;

			case "TODAS LAS CATEGORIAS":

				// Si no se escoge ninguna categoria se muestran todos los productos
				manejador.actualizarProductosObservables();
				tablaProductos.setItems(manejador.getProductosObservables());
				;
				break;

			}
		} else {

			Utilidades.mostrarMensaje("Elija una categoria", "Por favor elija una categoria");
		}

	}

	@FXML
	void verDetalleProducto(ActionEvent event) {

		manejador.cargarEscenaDetallesProductos(producto);

	}

	@FXML
	void initialize() {

		ObservableList<String> categorias = FXCollections.observableArrayList();

		categorias.add("TECNOLOGIA");
		categorias.add("DEPORTE");
		categorias.add("MODA");
		categorias.add("LIBROS");
		categorias.add("JOYAS");
		categorias.add("TODAS LAS CATEGORIAS");

		cbxCategoria.setItems(categorias);

		clumnaCedula.setCellValueFactory(productoCelda -> productoCelda.getValue().getCedulaUsuario());
		columnaNombre.setCellValueFactory(productoCelda -> productoCelda.getValue().getNombre());
		columnaId.setCellValueFactory(productoCelda -> productoCelda.getValue().getId());
		columnaCategoria.setCellValueFactory(productoCelda -> productoCelda.getValue().getCategoria());
		columnaPrecio.setCellValueFactory(productoCelda -> productoCelda.getValue().getPrecio());

		tablaProductos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> activarDetallesProducto(newValue));

	}

	/**
	 * Se activa cuando se selecciona un producto y se abre la ventana de detalle
	 * del producto
	 * 
	 * @param productoObservable
	 */
	private void activarDetallesProducto(ProductoObservable productoObservable) {

		
		btnDetalles.setDisable(false);

		this.producto = productoObservable;
		
		

	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {

		this.manejador = manejador;

		tablaProductos.setItems(manejador.getProductosObservables());
	}

	/**
	 * Realiza la operacion que llama a los metodos para filtrar la categoria, y su
	 * debida validacion
	 * 
	 * @param categoria
	 */
	public void auxliarFiltrarPorCategoria(Categoria categoria) {

		manejador.actualizarProductosObservablesPorCategoria(categoria);

		if (manejador.getProductosObservables() != null) {

			tablaProductos.setItems(manejador.getProductosObservables());

		} else {

			Utilidades.mostrarMensaje("Categoria", "No hay productos registrados para esta categoria");

			manejador.actualizarProductosObservables();
			tablaProductos.setItems(manejador.getProductosObservables());

		}

	}

}