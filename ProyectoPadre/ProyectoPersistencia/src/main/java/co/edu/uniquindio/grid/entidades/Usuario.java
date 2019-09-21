package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vendedor
 *
 */

@Entity
@Table(name = "Usuarios")
public class Usuario extends Persona implements Serializable {

	
	@OneToMany(mappedBy = "usuario")
	private List<Calificacion> calificaciones;
	
	@OneToMany(mappedBy = "usuario")
	private List<Compra> compras;
	
	@OneToMany(mappedBy = "usuario")
	private List<Favorito> favoritos;
	
	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentario;
	
	@Enumerated(EnumType.STRING)
    private Rol rol;
	
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

	
}
