package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.PruebaDelegado;

public class iniciarSesion implements Initializable {

	private PruebaDelegado pruebaDelegado;

	String clave;
	String correo;

	@FXML
	private TextField email;

	@FXML
	private Button btnIngresar;

	@FXML
	private TextField contrasenia;

	@FXML
	void validarDatos(ActionEvent event) {

		String clave = contrasenia.getText();

		String correo = email.getText();


		pruebaDelegado = PruebaDelegado.pruebaDelegado;

		if (clave != null && correo != null) {

			try {
				System.out.println(pruebaDelegado.autenticarUsuario(correo, clave).toString());
			} catch (NoExisteElementosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

//		String clave = txtClave.getText();
//
//		String correo = email.getText();

	}

}
