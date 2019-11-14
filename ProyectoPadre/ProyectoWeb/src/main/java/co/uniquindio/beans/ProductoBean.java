package co.uniquindio.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;

import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;

@ManagedBean("productoBean")
@ApplicationScoped
public class ProductoBean {

	private String nombre;
	private String descripcion;
	private String disponibilidad;

	@Positive
	private String precio;

	@Future
	private Date fechaLimite;
	private Categoria categoria;
	private List<Categoria> listaCategorias;

	private List<Producto> listaProductos;

	@PostConstruct
	public void inicializar() {

		this.listaCategorias = adminEJB.listarCategorias();

		this.listaProductos = adminEJB.listarTodosLosProductos();

	}

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

			Producto p = new Producto(vendedor, descripcion, prec, dispo, fechaLimite, nombre, categoria);

			adminEJB.crearPrducto(p);

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Operacion exitosa");

			FacesContext.getCurrentInstance().addMessage(null, m);

			return "nuevoUsuario";

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

}
