package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;

public class Lanzador extends Application {

	/**
	 * Se lanza la aplicaicion
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Se carga y edita el escenario inicial de la app
	 * 
	 * @param primaryStage escenario de a la aplicacion
	 * @throws Exception en caso de no poder cargar el escenario
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new ManejadorEscenarios(primaryStage);
	}
}
