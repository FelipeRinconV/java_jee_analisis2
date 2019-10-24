package controlador;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;

public class DetallesController {

	
	private ManejadorEscenarios manejador;

	private Stage estage;
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView vistaImagen;

	@FXML
	private JFXButton btnCargarImagen;

	@FXML
	void cargarImagen(ActionEvent event) {

		vistaImagen.setImage(new Image("http://localhost:8080/product_desing.png"));

	}

	@FXML
	void initialize() {
		assert vistaImagen != null : "fx:id=\"vistaImagen\" was not injected: check your FXML file 'Untitled'.";
		assert btnCargarImagen != null : "fx:id=\"btnCargarImagen\" was not injected: check your FXML file 'Untitled'.";

	}

	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

	public Stage getEstage() {
		return estage;
	}

	public void setEstage(Stage estage) {
		this.estage = estage;
	}
	
	
	
	
}