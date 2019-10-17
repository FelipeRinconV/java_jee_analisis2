package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lanzador extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/ventanaIniciarSesion.fxml"));

		Parent p = loader.load();

		Scene s = new Scene(p);

		primaryStage.setScene(s);

		primaryStage.show();

	}
	
	public static void main(String []a) {
		launch(a);
	}

}
