package modelo;



import co.edu.uniquindio.grid.entidades.Rol;
import co.edu.uniquindio.grid.entidades.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UsuarioObservable {

	private StringProperty cedula;

	private StringProperty nombreCompleto;

	private StringProperty email;

	private StringProperty numeroTelefono;

	private StringProperty direccion;


	private Rol rol;

	public UsuarioObservable(Usuario usuario) {

		
		this.cedula=new SimpleStringProperty(usuario.getCedula());
		this.email=new SimpleStringProperty(usuario.getEmail());
		this.nombreCompleto=new SimpleStringProperty(usuario.getNombreCompleto());
		
	}

	public StringProperty getCedula() {
		return cedula;
	}

	public void setCedula(StringProperty cedula) {
		this.cedula = cedula;
	}

	public StringProperty getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(StringProperty nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(StringProperty numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public StringProperty getDireccion() {
		return direccion;
	}

	public void setDireccion(StringProperty direccion) {
		this.direccion = direccion;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
