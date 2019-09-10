package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad  que representara la clase persona
 *
 */
@Entity
public class Persona implements Serializable {

	@Id
	private String Cedula;
	
	
	@Column(nullable = false)
	private int Edad;
	
	@Column(length = 35)
	private String Nombre;
	
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@ElementCollection 
	private List<String> contactos;
	
	
	
	
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

	private static final long serialVersionUID = 1L;

	public Persona() {
		super();
	}   
	public String getCedula() {
		return this.Cedula;
	}

	public void setCedula(String Cedula) {
		this.Cedula = Cedula;
	}   
	public int getEdad() {
		return this.Edad;
	}

	public void setEdad(int Edad) {
		this.Edad = Edad;
	}   
	public String getNombre() {
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public List getContactos() {
		return contactos;
	}
	public void setContactos(List contactos) {
		this.contactos = contactos;
	}
   
	
}
