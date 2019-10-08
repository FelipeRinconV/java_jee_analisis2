package co.edu.uniquindio.grid.dto;

public class UsuarioGastosComprasDTO {

	private String nombreUsuario;

	private Long gastosCompras;

	public UsuarioGastosComprasDTO(String nombre, Long gastos) {

		this.nombreUsuario = nombre;

		this.gastosCompras = gastos;

	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Long getGastosCompras() {
		return gastosCompras;
	}

	public void setGastosCompras(Long gastosCompras) {
		this.gastosCompras = gastosCompras;
	}

	@Override
	public String toString() {
		return "UsuarioGastosComprasDTO [nombreUsuario=" + nombreUsuario + ", gastosCompras=" + gastosCompras + "]";
	}

	
	
}
