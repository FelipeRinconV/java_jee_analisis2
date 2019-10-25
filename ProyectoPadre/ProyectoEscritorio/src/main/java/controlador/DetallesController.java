package controlador;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;
import modelo.ProductoObservable;

public class DetallesController {

	private ProductoObservable producto;

	private ManejadorEscenarios manejador;

	private Stage stage;

	@FXML
	private Label labelPuntuacion;

	@FXML
	private Label labelPrecio;

	@FXML
	private Text txtDescripcion;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXButton btnImagen1;

	@FXML
	private JFXButton btnImagen2;

	@FXML
	private JFXButton btnImagen3;

	@FXML
	private JFXButton btnImagen4;

	@FXML
	private ImageView imagenPrincipal;

	@FXML
	void ponerImagen1(ActionEvent event) {

		imagenPrincipal.setImage(new Image("http://localhost:8080/mac1.jpg"));

	}

	@FXML
	void ponerImagen2(ActionEvent event) {

		imagenPrincipal.setImage(new Image("http://localhost:8080/mac2.jpg"));

	}

	@FXML
	void ponerImagen3(ActionEvent event) {

		imagenPrincipal.setImage(new Image("http://localhost:8080/mac3.jpg"));

	}

	@FXML
	void ponerImagen4(ActionEvent event) {

		imagenPrincipal.setImage(new Image("http://localhost:8080/mac4.jpg"));

	}

	@FXML
	void initialize() {


		imagenPrincipal.setImage(new Image("http://localhost:8080/mac1.jpg"));
		assert btnImagen1 != null : "fx:id=\"btnImagen1\" was not injected: check your FXML file 'detallesProducto.fxml'.";
		assert btnImagen2 != null : "fx:id=\"btnImagen2\" was not injected: check your FXML file 'detallesProducto.fxml'.";
		assert btnImagen3 != null : "fx:id=\"btnImagen3\" was not injected: check your FXML file 'detallesProducto.fxml'.";
		assert btnImagen4 != null : "fx:id=\"btnImagen4\" was not injected: check your FXML file 'detallesProducto.fxml'.";
		assert imagenPrincipal != null : "fx:id=\"imagenPrincipal\" was not injected: check your FXML file 'detallesProducto.fxml'.";

	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public ProductoObservable getProducto() {
		return producto;
	}

	public void setProducto(ProductoObservable producto) {
		this.producto = producto;
	}

}