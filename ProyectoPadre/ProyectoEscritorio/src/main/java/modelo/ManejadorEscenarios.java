package modelo;

import java.io.IOException;

import com.sun.enterprise.module.bootstrap.Main;

import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;
import controlador.UsuarioController;
import controlador.iniciarSesion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Permite manejar los scenarios
 * 
 * @author felipe
 *
 */
public class ManejadorEscenarios {

	/**
	 * Contenedor principal de la gui
	 */
	private Stage escenario;

	/**
	 * Tipo de panel incial
	 */

	private BorderPane borderPanel;

	/**
	 * Para almacenar los usuario sobservables
	 */

	private ObservableList<UsuarioObservable> usuariosObservables;

	/**
	 * COnexion con la capa de negocio
	 */

	private PruebaDelegado administradorDelegado;

	/**
	 * recibe el escenario principal de la aplicacion
	 * 
	 * @param escenario inicial
	 * @throws NoExisteElementosException
	 */

	public ManejadorEscenarios(Stage escenario) {

		this.escenario = escenario;

		administradorDelegado = PruebaDelegado.pruebaDelegado;
		usuariosObservables = FXCollections.observableArrayList();

		try {
			// se inicializa el escenario
			escenario.setTitle("Unimarket/iniciar sesion");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/inicio.fxml"));

			borderPanel = (BorderPane) loader.load();

			// se carga la escena
			Scene scene = new Scene(borderPanel);
			escenario.setScene(scene);
			escenario.show();

			System.out.println("antes de cargar la scena");

			cargarEscenaListaUsuarios();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 * VENTANA QUE SE ESTA CARGANDO CUANDO SE INICIA EL LA APLICACION 
	 * @throws NoExisteElementosException
	 */
	public void cargarEscenaListaUsuarios() {

		try {

			usuariosObservables = administradorDelegado.listarEmpleadosObservables();

			FXMLLoader loader2 = new FXMLLoader();
			// PONER LA RUTA DE LA VISTA
			loader2.setLocation(getClass().getResource("/listarUsuarios.fxml"));
			AnchorPane panelAncho = (AnchorPane) loader2.load();
			borderPanel.setCenter(panelAncho);

			UsuarioController controlador = loader2.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public BorderPane getBorderPanel() {
		return borderPanel;
	}

	public void setBorderPanel(BorderPane borderPanel) {
		this.borderPanel = borderPanel;
	}

	public ObservableList<UsuarioObservable> getUsuariosObservables() {
		return usuariosObservables;
	}

	public void setUsuariosObservables(ObservableList<UsuarioObservable> usuariosObservables) {
		this.usuariosObservables = usuariosObservables;
	}

	public PruebaDelegado getAdministradorDelegado() {
		return administradorDelegado;
	}

	public void setAdministradorDelegado(PruebaDelegado administradorDelegado) {
		this.administradorDelegado = administradorDelegado;
	}

}
