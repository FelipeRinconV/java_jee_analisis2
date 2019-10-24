package modelo;

import co.edu.uniquindio.grid.entidades.Producto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductoObservable {

	private StringProperty cedulaUsuario;

	private StringProperty nombre;

	private StringProperty id;

	private StringProperty precio;

	private StringProperty categoria;

	public ProductoObservable(Producto producto) {

		this.cedulaUsuario = new SimpleStringProperty(producto.getUsuario().getCedula());
		this.nombre = new SimpleStringProperty(producto.getNombre());
		this.id = new SimpleStringProperty(String.valueOf(producto.getIdProducto()));
		this.precio = new SimpleStringProperty(String.valueOf(producto.getPrecio()));
		this.categoria = new SimpleStringProperty(producto.getTipo().toString());

	}

	public StringProperty getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(StringProperty cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public StringProperty getNombre() {
		return nombre;
	}

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public StringProperty getId() {
		
		return id;
	}



	public StringProperty getPrecio() {
		return precio;
	}

	public void setPrecio(StringProperty precio) {
		this.precio = precio;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public StringProperty getCategoria() {
		return categoria;
	}

	public void setCategoria(StringProperty categoria) {
		this.categoria = categoria;
	}

}
