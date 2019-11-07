package co.uniquindio.beans;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.grid.entidades.TipoPago;
import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;

@ManagedBean("productoBean")
@ApplicationScoped
public class ProductoBean {

	private String nombre;
	private String descripcion;
	private String disponibilidad;
	private String precio;
	private Date fechaLimite;
	private Categoria categoria;

	@EJB
	private AdminEJB adminEJB;

	public String crearProducto() {

		try {

			double prec = Double.parseDouble(precio);

			boolean dispo = true;

			if (disponibilidad.equals("1")) {

				dispo = true;
			} else if (disponibilidad.equals("2")) {

				dispo = false;
			}

			Usuario vendedor = (Usuario) adminEJB.darPersonaPorCedula("1040");

			Producto p = new Producto(vendedor, descripcion, prec, dispo, fechaLimite, nombre, Categoria.DEPORTE);

			adminEJB.crearPrducto(p);
			
			
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro exitoso", "Operacion exitosa");

			FacesContext.getCurrentInstance().addMessage(null, m);
			
			return "nuevoProducto";

		} catch (ElementoRepetidoException e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());

			FacesContext.getCurrentInstance().addMessage(null, m);
		} catch (Exception e) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());

			FacesContext.getCurrentInstance().addMessage(null, m);
		}

		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String isDisponibilidad() {
		return disponibilidad;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public AdminEJB getAdminEJB() {
		return adminEJB;
	}

	public void setAdminEJB(AdminEJB adminEJB) {
		this.adminEJB = adminEJB;
	}

	
	/**
	 * Devuelve la lista de categorias
	 * @return
	 */
	public Categoria[] getListaTipos() {
		
		return Categoria.values();
		
	}
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

}
