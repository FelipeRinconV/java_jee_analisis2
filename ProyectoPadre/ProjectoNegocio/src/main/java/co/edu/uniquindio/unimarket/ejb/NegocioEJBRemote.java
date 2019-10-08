package co.edu.uniquindio.unimarket.ejb;

import javax.ejb.Remote;

import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.entidades.Usuario;

@Remote
public interface NegocioEJBRemote {

	Persona autenticarUsuario(String email, String contraseña);
}
