package co.uniquindio.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;

import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;

@Named(value = "usuarioBean")
@RequestScoped
class UsuarioBean {

	@EJB
	private AdminEJB adminEJB;

	//
	private boolean alerta;
	private String mensaje;

	/**
	 * Validar la presentacion
	 */
	@Size(min = 9, max = 10, message = "La cedula debe tener entre 9 y 10 caracteres ")
	private String cedula;

	private String nombre;

	private String numeroTelefono;

	private String direccion;

	private String contrasenia;

	private String email;

	public void registrarUsuario() {

		Usuario user = new Usuario(nombre, numeroTelefono, direccion, contrasenia, email, cedula);

	}

	public AdminEJB getAdminEJB() {
		return adminEJB;
	}

	public void setAdminEJB(AdminEJB adminEJB) {
		this.adminEJB = adminEJB;
	}

	public boolean isAlerta() {
		return alerta;
	}

	public void setAlerta(boolean alerta) {
		this.alerta = alerta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
