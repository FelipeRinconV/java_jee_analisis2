package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad que representara la clase persona,esta clase sera el comun
 * denominador de los roles en el sistema unimark
 *
 */

@Entity
@Inheritance
public class Persona implements Serializable {

	@OneToMany(mappedBy = "persona")
	private List<Producto> productos;

	/**
	 * Cedula el cual se utilizara como id de la tabla persona
	 */
	@Id
	private String Cedula;

	/**
	 * Nombre completo de la persona
	 */
	@Column(length = 200)
	private String nombreCompleto;

	@Column(length = 200)
	private String email;

	@Column(length = 200)
	private String numeroTelefono;

	@Column(length = 200)
	private String direccion;

	@Column(length = 200)
	private String contraseña;

	private static final long serialVersionUID = 1L;

	// Contructor necesario para la entidad
	public Persona() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Cedula == null) ? 0 : Cedula.hashCode());
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
		if (Cedula == null) {
			if (other.Cedula != null)
				return false;
		} else if (!Cedula.equals(other.Cedula))
			return false;
		return true;
	}

	// Inicio de get y set

	public String getCedula() {
		return Cedula;
	}

	public void setCedula(String cedula) {
		Cedula = cedula;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
