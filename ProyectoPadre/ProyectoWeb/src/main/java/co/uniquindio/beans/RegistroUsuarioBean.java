package co.uniquindio.beans;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;

@ManagedBean("registroUsuarioBean")
@ApplicationScoped
public class RegistroUsuarioBean {

	@EJB
	private AdminEJB adminEJB;

	private String nombre;

	private String contra;

	private String email;
	private String cedula;
	private String telefono;
	private String direccion;

	public String registrarUsuario() {

		
		System.out.println("NOMBRE EN BEAN: " + nombre);
		
		
		if (nombre.length() > 1 && contra.length() > 1 && email.length() > 1) {

			Usuario usuarioNuevo = new Usuario();

			usuarioNuevo.setNombreCompleto(nombre);
			usuarioNuevo.setCedula(cedula);
			usuarioNuevo.setContrasenia(contra);
			usuarioNuevo.setEmail(email);
			usuarioNuevo.setDireccion(direccion);
			usuarioNuevo.setNumeroTelefono(telefono);

			try {
				adminEJB.registrarUsuario(usuarioNuevo);
				
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro exitoso","Registro exitoso");

				FacesContext.getCurrentInstance().addMessage(null, m);
				return "pag1?faces-redirect=true";
			} catch (ElementoRepetidoException e) {
				// TODO Auto-generated catch block
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());

				FacesContext.getCurrentInstance().addMessage(null, m);

			}
		} else {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese todos los datos",
					"Ingrese todos los datos");

			FacesContext.getCurrentInstance().addMessage(null, m);

		}

		return "";

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
