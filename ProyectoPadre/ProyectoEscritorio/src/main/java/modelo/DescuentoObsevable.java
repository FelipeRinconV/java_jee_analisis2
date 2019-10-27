package modelo;

import co.edu.uniquindio.grid.entidades.Descuento;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DescuentoObsevable {

	private Descuento descuentoAsociado;
	
	private StringProperty idDescuento;

	private StringProperty categoria;

	private StringProperty procentaje;

	private StringProperty activo;

	private StringProperty cedula_administrador;

	public DescuentoObsevable(Descuento descuento) {

		this.descuentoAsociado=descuento;
		
		this.idDescuento = new SimpleStringProperty(String.valueOf(descuento.getId()));

		this.categoria = new SimpleStringProperty(descuento.getCategoria().toString());

		this.procentaje = new SimpleStringProperty(String.valueOf(descuento.getPorcentaje()));

		if (descuento.isActivo()) {

			this.activo = new SimpleStringProperty("activo");
		} else {
			this.activo = new SimpleStringProperty("desactivado");
		}

		this.cedula_administrador = new SimpleStringProperty(descuento.getAdministrador().getCedula());

	}

	public Descuento getDescuentoAsociado() {
		return descuentoAsociado;
	}

	public void setDescuentoAsociado(Descuento descuentoAsociado) {
		this.descuentoAsociado = descuentoAsociado;
	}

	public StringProperty getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(StringProperty idDescuento) {
		this.idDescuento = idDescuento;
	}

	public StringProperty getCategoria() {
		return categoria;
	}

	public void setCategoria(StringProperty categoria) {
		this.categoria = categoria;
	}

	public StringProperty getProcentaje() {
		return procentaje;
	}

	public void setProcentaje(StringProperty procentaje) {
		this.procentaje = procentaje;
	}

	public StringProperty getActivo() {
		return activo;
	}

	public void setActivo(StringProperty activo) {
		this.activo = activo;
	}

	public StringProperty getCedula_administrador() {
		return cedula_administrador;
	}

	public void setCedula_administrador(StringProperty cedula_administrador) {
		this.cedula_administrador = cedula_administrador;
	}
	
	
	

}
