package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import UtilidadesAlert.Utilidades;
import co.edu.uniquindio.grid.entidades.Administrador;
import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.grid.entidades.Descuento;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import modelo.ManejadorEscenarios;

public class EdicionDescuentoController {

	private ManejadorEscenarios manejador;

	private Stage stage;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXButton btnAgregar;

	@FXML
	private JFXTextField txtPorcentaje;

	@FXML
	private JFXComboBox<Categoria> cbxCategoria;

	@FXML
	private JFXCheckBox cbxActivo;

	// Selecciono el activado
	@FXML
	void activarDescuento(ActionEvent event) {

	}

	@FXML
	void agregarDescuento(ActionEvent event) {

		Categoria cate = cbxCategoria.getSelectionModel().getSelectedItem();

		if (cate != null && txtPorcentaje.getText().length() > 0) {

			Descuento desc = new Descuento();

			Double procentaje = Double.parseDouble(txtPorcentaje.getText());

			desc.setPorcentaje(procentaje);

			desc.setCategoria(cate);

			if (cbxActivo.isSelected()) {

				try {

					boolean cent = manejador.aplicarDescuento(desc);

					if (cent) {

						desc.setActivo(true);

						Utilidades.mostrarMensaje("exito",
								"El descuento del: " + desc.getPorcentaje() + " % fue agregado y aplicado con exito");

					} else {

						Utilidades.mostrarMensaje("precaucion",
								"El descuento del: " + desc.getPorcentaje()
										+ " % fue agregado pero no se puede activar " + "\n"
										+ "debido a que no hay productos en la categoria del descuento");

						desc.setActivo(false);

					}
				} catch (NoExisteElementosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			try {

				// Agregamos el administrado que ingreso el descuento
				Administrador adminActual = (Administrador) manejador.getAdmin();
				desc.setAdministrador(adminActual);

				manejador.agregarDescuento(desc);
				
				manejador.actualizarDescuentosObservables();

				stage.close();

			} catch (ElementoRepetidoException e) {
				// TODO Auto-generated catch block
				Utilidades.mostrarMensaje("ELEMENTO REPETIDO", "");
			}

		} else {

			Utilidades.mostrarMensaje("ERROR", "Por favor ingrese todos los datos");
		}

	}

	@FXML
	void initialize() {

		ObservableList<Categoria> categoariasObservables = FXCollections.observableArrayList();

		categoariasObservables.add(Categoria.DEPORTE);
		categoariasObservables.add(Categoria.JOYAS);
		categoariasObservables.add(Categoria.LIBROS);
		categoariasObservables.add(Categoria.MODA);
		categoariasObservables.add(Categoria.TECNOLOGIA);
		categoariasObservables.add(Categoria.TECNOLOGIA);

		cbxCategoria.setItems(categoariasObservables);
		assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert txtPorcentaje != null : "fx:id=\"txtPorcentaje\" was not injected: check your FXML file 'agregarUsuario.fxml'.";
		assert cbxCategoria != null : "fx:id=\"cbxCategoria\" was not injected: check your FXML file 'agregarUsuario.fxml'.";

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

}
