package co.uniquindio.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import co.edu.uniquindio.grid.entidades.Usuario;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;

@Named(value = "usuarioBean")
@RequestScoped class UsuarioBean {

	@EJB
	private AdminEJB adminEJB;
	

	//
	private boolean alerta;
	private String mensaje;
	
	
	private String cedula,nombreCompleto,numeroTelefono,direccion,contrasenia,email;
	
	
	public void registrarUsuario() {
		
		
		Usuario user=new Usuario();
		
		
		
		
	}
	
}
