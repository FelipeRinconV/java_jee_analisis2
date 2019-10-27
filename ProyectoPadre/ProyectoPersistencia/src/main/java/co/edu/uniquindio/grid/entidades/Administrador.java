package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 * ENTIDAD QUE REPRESENTA A LOS ADMISTRADORES DE LA PAGINA SE UTILIZARO PORSTERIORME PARA LA SEGURIDAD 
 */
@Entity
@NamedQueries({@NamedQuery(name =Administrador.CONTAR_ADMINISTRADORES,query = "select count(p) from Administrador p")})
public class Administrador extends Persona implements Serializable {
	
	/**
	 * Query que permite contar los administrados que esten registrados en la base de datos
	 */
	public static final String CONTAR_ADMINISTRADORES="contar_administradores";
	
	private static final long serialVersionUID = 1234;

	@OneToMany(mappedBy = "administrador")
	private List<Descuento> descuentos;
	
	public Administrador() {
		super();
	}

	public List<Descuento> getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(List<Descuento> descuentos) {
		this.descuentos = descuentos;
	}
	
	
	
   
}
