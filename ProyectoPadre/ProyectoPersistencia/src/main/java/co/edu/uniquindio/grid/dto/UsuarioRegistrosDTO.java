package co.edu.uniquindio.grid.dto;

public class UsuarioRegistrosDTO {

	/**
	 * DTO con cédula del usuario, el email y número de registros.
	 */
	private String cedula;

	private String email;

	private Long numeroRegistros;

	public UsuarioRegistrosDTO(String cedula, String email, Long numeroRegistros) {

		this.cedula = cedula;
		this.email = email;
		this.numeroRegistros = numeroRegistros;

	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Long getNumeroRegistros() {
		return numeroRegistros;
	}

	public void setNumeroRegistros(Long numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}

	@Override
	public String toString() {
		return "UsuarioRegistrosDTO [cedula=" + cedula + ", email=" + email + ", numeroRegistros=" + numeroRegistros
				+ "]";
	}

}
