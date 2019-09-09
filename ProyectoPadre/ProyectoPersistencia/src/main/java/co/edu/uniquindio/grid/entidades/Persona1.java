package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Persona1
 *
 */
@Entity

public class Persona1 implements Serializable {

	@Id
	private String cedula;

	@Column(length = 25)
	private String nombre;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@NotNull
	private Sexo sexo;
	
	private List<String> contactos;

	private static final long serialVersionUID = 1L;

	public Persona1() {
		super();
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public List<String> getContactos() {
		return contactos;
	}

	public void setContactos(List<String> contactos) {
		this.contactos = contactos;
	}

	
}
