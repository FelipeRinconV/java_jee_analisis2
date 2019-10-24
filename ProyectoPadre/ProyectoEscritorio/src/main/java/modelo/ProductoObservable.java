package modelo;

import co.edu.uniquindio.grid.entidades.Producto;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductoObservable {

	private StringProperty cedulaUsuario;

	private StringProperty nombre;

	private IntegerProperty id;

	private IntegerProperty precio;

	private StringProperty categoria;

	public ProductoObservable(Producto producto) {

		this.cedulaUsuario = new SimpleStringProperty(producto.getUsuario().getCedula());
		this.nombre = new SimpleStringProperty(producto.getNombre());
		this.id = new SimpleIntegerProperty(producto.getIdProducto());
		this.precio = new SimpleIntegerProperty(producto.getPrecio());
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

	public IntegerProperty getId() {
		return id;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public IntegerProperty getPrecio() {
		return precio;
	}

	public void setPrecio(IntegerProperty precio) {
		this.precio = precio;
	}

	public BooleanProperty getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(BooleanProperty disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public StringProperty getCategoria() {
		return categoria;
	}

	public void setCategoria(StringProperty categoria) {
		this.categoria = categoria;
	}

}
