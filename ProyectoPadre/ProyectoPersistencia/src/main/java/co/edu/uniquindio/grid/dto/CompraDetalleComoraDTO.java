package co.edu.uniquindio.grid.dto;

import co.edu.uniquindio.grid.entidades.Compra;
import co.edu.uniquindio.grid.entidades.DetalleCompra;

public class CompraDetalleComoraDTO {

	Compra compra;
	
	DetalleCompra detalle;
	
	public CompraDetalleComoraDTO(Compra compra,DetalleCompra detalles) {
		
		this.compra=compra;
		this.detalle=detalles;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public DetalleCompra getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleCompra detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "CompraDetalleComoraDTO [compra=" + compra.getIdCompra()+ ", detalle=" + detalle.getId_detalle() + "]";
	}
	
	
	
}
