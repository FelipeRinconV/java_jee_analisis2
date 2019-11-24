package co.uniquindio.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.primefaces.event.FileUploadEvent;

import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.grid.entidades.Producto;
import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;
import co.edu.uniquindio.unimarket.excepciones.ElementoRepetidoException;

@ManagedBean("productoBean")
@ApplicationScoped
public class ProductoBean {

	@Size(min = 4, max = 50, message = "Mensaje de validacion")
	private String nombre;
	private String descripcion;

	private Producto productoSeleccionado;

	
	@ManagedProperty(value = "#{seguridadBean.usuario}")
	@Inject
	private Usuario vendedor;

	private String precio;

	@Future
	private Date fechaLimite;
	private Categoria categoria;
	private List<Categoria> listaCategorias;

	private List<String> imagenes;

	private List<Producto> listaProductos;

	@PostConstruct
	public void inicializar() {

		this.listaCategorias = adminEJB.listarCategorias();

		this.listaProductos = adminEJB.listarTodosLosProductos();

		this.imagenes = new ArrayList<String>();
	}

	@EJB
	private AdminEJB adminEJB;

	public String crearProducto() {

		try {

			double prec = Double.parseDouble(precio);

			if (vendedor.getCedula().length()>2) {

				Producto p = new Producto(vendedor, descripcion, prec, fechaLimite, nombre, categoria);
				// Le añadimos las imagenes
				p.setUrlImagenes(imagenes);
				adminEJB.crearPrducto(p);

				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Operacion exitosa");

				FacesContext.getCurrentInstance().addMessage(null, m);

				return "pag1";
			} else {

				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL VENDEDOR ES NULO ",
						"EL VENDEDOR ES NULO");

				FacesContext.getCurrentInstance().addMessage(null, m);

			}

		} catch (ElementoRepetidoException e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());

			FacesContext.getCurrentInstance().addMessage(null, m);
		} catch (Exception e) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());

			FacesContext.getCurrentInstance().addMessage(null, m);
		}

		return null;
	}

	public void subirImagen(FileUploadEvent file) {

		try {
			InputStream f = file.getFile().getInputstream();
			FileOutputStream fo = new FileOutputStream(
					new File("/home/felipe/Documentos/analisis_2/glassfish5/glassfish/domains/domain1/docroot/"
							+ file.getFile().getFileName()));
			String img = "http://localhost:8080/" + file.getFile().getFileName();

			// Agregamos las imagenes al producto
			imagenes.add(img);
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = f.read(buffer)) > 0) {
				fo.write(buffer, 0, bytesRead);

			}

			fo.flush();
			fo.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		FacesMessage msg = new FacesMessage("Successful", file.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public List<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}

}
