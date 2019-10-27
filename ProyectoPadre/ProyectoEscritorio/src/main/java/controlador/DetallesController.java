package controlador;

import com.jfoenix.controls.JFXButton;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;

import java.net.URL;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;
import modelo.ProductoObservable;

public class DetallesController {

	private Producto producto;

	private ManejadorEscenarios manejador;

	private Stage stage;

	private List<String> imagenesProducto;

	@FXML
	private Text txtNombre;

	@FXML
	private Text txtPrecio;

	@FXML
	private Text txtPuntuacion;

	@FXML
	private Text txtCategoria;

	@FXML
	private Text txtVendedor;

	@FXML
	private Text txtCantidad;

	@FXML
	private Text txtDescripcion;

	@FXML
	private Text txtFecha;
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

		imagenPrincipal.setImage(new Image(imagenesProducto.get(0)));

	}

	@FXML
	void ponerImagen2(ActionEvent event) {

		imagenPrincipal.setImage(new Image(imagenesProducto.get(1)));

	}

	@FXML
	void ponerImagen3(ActionEvent event) {

		imagenPrincipal.setImage(new Image(imagenesProducto.get(2)));

	}

	@FXML
	void ponerImagen4(ActionEvent event) {

		imagenPrincipal.setImage(new Image(imagenesProducto.get(3)));

	}

	@FXML
	void initialize() {

		imagenesProducto = new ArrayList<String>();

		// Se iniacian y cargan las url de las imagenes

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

	public Producto getProducto() {
		return producto;
	}

	/**
	 * 
	 * @param productoObservable
	 */
	public void setProducto(ProductoObservable productoObservable) {

		try {

			if (productoObservable != null) {
				int id = Integer.parseInt(productoObservable.getId().getValue());

				this.producto = manejador.darProuctoPorId(id);

				actualizarCampos();

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteElementosException e) {
			// TODO Auto-generated catch block
			Utilidades.mostrarMensaje("NO ENCONTRADO",
					"El producto por el id: " + productoObservable.getId().getValue() + " no se encuentra registrado ");
		}

	}

	/**
	 * Da la fecha como cadena en formato yyyy/MM/dd
	 * 
	 * @param date
	 * @return un String con la fecha en el formato indicado anteriormente
	 */
	public String darFechaComoCadena(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaComoCadena = sdf.format(date);

		return fechaComoCadena;
	}

	/**
	 * Actualiza lso campos de la ventana
	 */
	public void actualizarCampos() {

		if (producto != null) {

			imagenesProducto = producto.getUrlImagenes();

			imagenPrincipal.setImage(new Image(imagenesProducto.get(0)));
			txtCategoria.setText(producto.getTipo().toString());

			txtPrecio.setText("$ " + darTextoEnFormatoDePesos(String.valueOf(producto.getPrecio())));

			txtNombre.setText(producto.getNombre());
		
		try {
			txtPuntuacion.setText("Puntuacion: " + manejador.darPuntuacionProducto(producto.getIdProducto()));
		} catch (NoExisteElementosException e) {
			// TODO Auto-generated catch block
			Utilidades.mostrarMensaje("ERROR", "No hay productos registrados con este id");
		}

			txtCantidad.setText("Cantidad disponible: " + producto.getCantidadProducto());

			txtVendedor.setText("VENDEDOR: " + producto.getUsuario().getNombreCompleto());

			txtFecha.setText(txtFecha.getText() + " " + darFechaComoCadena(producto.getFechaLimite()));

			txtDescripcion.setText("Descripcion:" + producto.getDescripcion());

		} else {

			System.out.println("El producto es nulo");
		}
	}

	/**
	 * Metodo para dar formato en miles de pesos
	 * 
	 * @param precio
	 */
	public String darTextoEnFormatoDePesos(String precio) {
		
		//Se pasa a double para llamar al fomat(double) y no el format(object)
		double precioConverter=Double.parseDouble(precio);

		DecimalFormat formateador = new DecimalFormat("###,###.##");

		String salida=formateador.format(precioConverter);
		return salida;

	}

}