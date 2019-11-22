package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entidad que representara la clase persona,esta clase es el comun denominador
 * entre administradores y usuarios en el sistema unimark
 */

@Entity
@Inheritance
@Table(name = "Personas")
@NamedQueries({ @NamedQuery(name = Persona.LISTAR_PERSONAS, query = "select p from Persona p"),
		@NamedQuery(name = Persona.BUSCAR_POR_EMAIL, query = "select p from Persona p where p.email=:email"),
		@NamedQuery(name = Persona.BUSCAR_PERSONA_POR_EMAIL_Y_CONTRASENIA, query = "SELECT u  FROM  Persona u WHERE u.email=:email AND u.contrasenia=:contra")

})
public class Persona implements Serializable {

	/**
	 * Cedula el cual se utilizara como id de la tabla persona
	 */
	@Id
	private String cedula;

	// Query probado
	public static final String BUSCAR_PERSONA_POR_EMAIL_Y_CONTRASENIA = "buscar_usuario";

	public static final String LISTAR_PERSONAS = "listar_personas";

	public static final String BUSCAR_POR_EMAIL = "buscar_por_email";

	/**
	 * Nombre completo de la persona
	 */
	@Column(length = 300, nullable = false, name = "NOMBRE_COMPLETO")
	private String nombreCompleto;

	@Column(length = 200, nullable = false)
	private String email;

	@Column(length = 50, nullable = false, name = "NUMERO_TELEFONO")
	private String numeroTelefono;

	@Column(length = 200, nullable = false)
	private String direccion;

	@Column(length = 250, nullable = false,name ="CONTRASENIA")
	private String contrasenia;

	private static final long serialVersionUID = 123;

	// Contructor necesario para la entidad
	public Persona() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}

	// Inicio de get y set

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contraseña) {
		this.contrasenia = contraseña;
	}

	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombreCompleto=" + nombreCompleto + ", email=" + email + ", contraseña="
				+ contrasenia + "]";
	}

}
