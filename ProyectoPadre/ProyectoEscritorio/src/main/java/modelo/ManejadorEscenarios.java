package modelo;

import java.awt.Toolkit;
import java.io.IOException;

import com.sun.enterprise.module.bootstrap.Main;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;
import controlador.EdicionUsuarioController;
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

	/*
	 * Variable para guardar el ancho de pantalla donde se ejecute la aplicacion
	 */
	private int anchoPantalla;
	/*
	 * Variable para guardar el largo de la pantalla donde se ejecute la aplicacion
	 */
	private int largoPantalla;
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
	 * Para almacenar los productos pbservables
	 */
	
	private ObservableList<ProductoObservable>  productosObservables;
	
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

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Se obtiene el ancho de la pantalla
		anchoPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;

		// Se obtiene el largo de la pantalla
		largoPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

		this.escenario = escenario;

		administradorDelegado = PruebaDelegado.pruebaDelegado;
		usuariosObservables = FXCollections.observableArrayList();
		productosObservables=FXCollections.observableArrayList();

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

	public void cragarVentanaAdmin() {
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

			// Se pone la ventana a pantalla completa
			// se carga la escena
			Scene scene = new Scene(borderPanelAdmin);
			escenarioAdmin.setScene(scene);
			escenarioAdmin.setFullScreen(true);
			escenarioAdmin.show();

			// Se carga el controlador de la ventana administrador
			OpcionesAdministradorController controladorAdministrador = loader.getController();
			controladorAdministrador.setManejador(this);

			// Se carga la escena por defecto a la ventana de admin
			cargarEscenarioUsuarios();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Carga la escena de usuarios a la ventana del admin
	public void cargarEscenarioUsuarios() {

		try {

			usuariosObservables = administradorDelegado.listarUsuariosObservables();

			FXMLLoader lodaer = new FXMLLoader();
			// PONER LA RUTA DE LA VISTA
			lodaer.setLocation(getClass().getResource("/listarUsuarios.fxml"));

			AnchorPane panelAncho = (AnchorPane) lodaer.load();
			borderPanelAdmin.setCenter(panelAncho);

			// Se carga el controlador
			ListaUsuariosController listarUsuariosControlador = lodaer.getController();

			// Se le pasa el manejador al controlador
			listarUsuariosControlador.setManejador(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que carga la escene de la creacion de nuevos usuarios
	 */
	public void cargarEscenaAgregarUsuario() {
		// TODO Auto-generated method stub

		try {

			// Se carga la interfaz para la ventana

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/agregarUsuario.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Creamos el escenario para la escena
			Stage escenarioAgregar = new Stage();
			escenarioAgregar.setTitle("Crear usuario");

			Scene escena = new Scene(page);
			escenarioAgregar.setScene(escena);

			EdicionUsuarioController usuarioControlador = loader.getController();
			usuarioControlador.setManejador(this);
			usuarioControlador.setEscenarioEdicion(escenarioAgregar);

			// Se muestra el escenario de edicion del nuevo usuario
			escenarioAgregar.showAndWait();

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

	public BorderPane getBorderPanelAdmin() {
		return borderPanelAdmin;
	}

	public void setBorderPanelAdmin(BorderPane borderPanelAdmin) {
		this.borderPanelAdmin = borderPanelAdmin;
	}

	/**
	 * ---------------INICIO DE METODOS PARA CARGAR LA
	 * LOGICA-------------------------- Metodo para auntenticar en la ventana
	 * inicial
	 * 
	 * @param correo
	 * @param clave
	 * @return
	 */
	public Persona autenticarUsuario(String correo, String clave) {

		// verificamos si el usuario se encuentra en la base de datos
		Persona person = administradorDelegado.autenticarUsuario(correo, clave);
		return person;

	}

	/**
	 * Metodo para agregar un nuevo usuario
	 * 
	 * @param user
	 * @return true si el usuario es ingresado con exito
	 */
	public boolean registrarUsuario(Usuario user) {
		Usuario nuevo = null;
		try {

			nuevo = administradorDelegado.registrarUsuario(user);

		} catch (ElementoRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nuevo != null;
	}

	/**
	 * Agrega el nuevo usuario a la lista
	 * 
	 * @param user
	 */
	public void agregarUsuarioObservable(Usuario user) {

		UsuarioObservable nuevoUser = new UsuarioObservable(user);

		usuariosObservables.add(nuevoUser);

	}

	/**
	 * Elimina un usuario dada su cedula
	 * 
	 * @param usuario
	 * @return una persona si la operacion es exitosa null en caso contrario
	 */
	public Persona eliminarUsuario(String cedula) {

		try {
			return administradorDelegado.eliminarPersona(cedula);

		} catch (NoExisteElementosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo que remueve un usuario observable, se utiliza cuando es eliminado un usuario de la base de datos 
	 * @param user
	 */
	public void eliminarDeListaObservable(UsuarioObservable user) {

		usuariosObservables.remove(user);

	}
	
	/**
	 * Validacion de los correos
	 * @param correo
	 * @return
	 */
	public boolean validarCorreo(String correo) {
		
		return administradorDelegado.validarCorreo(correo);
	}

	public ObservableList<ProductoObservable> getProductosObservables() {
		return productosObservables;
	}

	public void setProductosObservables(ObservableList<ProductoObservable> productosObservables) {
		this.productosObservables = productosObservables;
	}

	
	
	
}
