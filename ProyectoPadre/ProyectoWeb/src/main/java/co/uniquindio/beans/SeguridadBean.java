package co.uniquindio.beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;
import co.edu.uniquindio.unimarket.excepciones.NoExisteElementosException;

@FacesConfig(version = Version.JSF_2_3)
@ManagedBean("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private AdminEJB adminEJB;

	// Para los detalles del producto
	private Producto productoSeleccionado;

	private String email;
	private String contrasenia;

	// Calificacion del producto seleccionado
	private Double calificacion;
	private Usuario usuario;
	private boolean autenticado;

	@PostConstruct
	public void inicialiazar() {

		this.productoSeleccionado = new Producto();

		this.usuario = new Usuario();
		this.autenticado = false;

	}

	public String seleccionarProducto(Producto p) {

		System.out.println("ENTRA A SELECCIONAR EL PRODUCTO : " + p.getNombre());
		this.productoSeleccionado = p;

		return "detalles?faces-redirect=true";

	}

	public String iniciarSesion() {

		// Tambien autentica administradores

		try {

			Usuario u = adminEJB.autenticarUsuario(email, contrasenia);

			if (u != null) {

				this.usuario = u;

				this.autenticado = true;
				return "pag1?faces-redirect=true";
			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"ERROR no se encontraton las credenciales: " + email + "-" + contrasenia,
								"No se ah encontrado el usuario con las credenciales ingresadas"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR INESPERADO", "Ah ocurrido un error"));
		}

		return null;

	}

	public AdminEJB getAdminEJB() {
		return adminEJB;
	}

	public void setAdminEJB(AdminEJB adminEJB) {
		this.adminEJB = adminEJB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

}
