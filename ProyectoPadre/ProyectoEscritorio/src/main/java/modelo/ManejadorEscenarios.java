package modelo;

import java.io.IOException;

import com.sun.enterprise.module.bootstrap.Main;

import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;
import controlador.ListaUsuariosController;
import controlador.OpcionesAdministradorController;
import controlador.UsuarioController;
import controlador.VentanaOpcionesController;
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
	 * Contenedor para la ventana principal del usuario
	 */
	
	private Stage escenarioAdmin;

	/**
	 * Tipo de panel incial
	 */

	private BorderPane borderPanel;
	
	

	/**
	 * Tipo de panel de la ventana principal del administrador
	 */
	private BorderPane borderPanelAdmin;

	/**
	 * Para almacenar los usuarios sobservables
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

			borderPanel.setMinSize(735, 445);
			// se carga la escena
			Scene scene = new Scene(borderPanel);
			escenario.setScene(scene);
			escenario.setResizable(false);
			escenario.show();

			cargarSceneaInicial();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario VENTANA QUE SE ESTA CARGANDO
	 * CUANDO SE INICIA EL LA APLICACION
	 */
	public void cargarSceneaInicial() {

		try {
			FXMLLoader loader2 = new FXMLLoader();
			// PONER LA RUTA DE LA VISTA
			loader2.setLocation(getClass().getResource("/ventanaIniciarSesion.fxml"));

			AnchorPane panelAncho = (AnchorPane) loader2.load();
			borderPanel.setCenter(panelAncho);

			iniciarSesion controlador = loader2.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cargarEscenarioOpciones() {
		// TODO Auto-generated method stub

		try {
			
			escenario.close();
			
		    escenarioAdmin = new Stage();
			// se inicializa el escenario
		    escenarioAdmin.setTitle("Unimarket/ventana opciones");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/prueba.fxml"));
			borderPanelAdmin = (BorderPane) loader.load();

			borderPanel.setMinSize(735, 445);
			// se carga la escena
			Scene scene = new Scene(borderPanelAdmin);
			escenarioAdmin.setScene(scene);
			escenarioAdmin.show();
			
			//Se carga el controlador
			OpcionesAdministradorController controladorAdministrador =loader.getController();
			controladorAdministrador.setManejador(this);

			//cargarSceneaInicial();

			

//			// Sacamos los usuarios observables
//			usuariosObservables = administradorDelegado.listarUsuariosObservables();
//
//			escenario.close();
//			// Cargamos la ventana
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(getClass().getResource("/prueba.fxml"));
//			borderPanelAdmin = (BorderPane) loader.load();
//
//			// Cargamos la escena central por defecto
//			FXMLLoader loader2 = new FXMLLoader();
//			loader2.setLocation(getClass().getResource("/listarUsuarios.fxml"));
//			AnchorPane escenaCentral = (AnchorPane) loader2.load();
//			borderPanelAdmin.setCenter(escenaCentral);
//
//			// se crea el escenario
//			Stage escenarioOpciones = new Stage();
//			escenarioOpciones.setTitle("Opciones");
//			escenarioOpciones.setFullScreen(true);
//
//			// creamos la scena
//			Scene escena = new Scene(borderPanelAdmin);
//			escenarioOpciones.setScene(escena);
//
//			// se carga el controlador
//			ListaUsuariosController listarUsuariosController = loader2.getController();
//
//			OpcionesAdministradorController controladorAdministrador =loader.getController();
//			
//			controladorAdministrador.setManejador(this);
//			
//			// se le pasa el escenario y los datos de los usuariosObservables
//			listarUsuariosController.setEscenarioListaUsuarios(escenarioOpciones);
//			listarUsuariosController.setManejador(this);
//
//			// se muestra el escenario
//			escenarioOpciones.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	public Persona autenticarUsuario(String correo, String clave) {

		// verificamos si el usuario se encuentra en la base de datos
		Persona person = administradorDelegado.autenticarUsuario(correo, clave);
		return person;

	}

	public BorderPane getBorderPanelAdmin() {
		return borderPanelAdmin;
	}

	public void setBorderPanelAdmin(BorderPane borderPanelAdmin) {
		this.borderPanelAdmin = borderPanelAdmin;
	}

}
