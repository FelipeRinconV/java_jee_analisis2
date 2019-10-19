package co.edu.uniquindio.prueba.test;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.persistence.EntityManager;

import org.glassfish.internal.api.Public;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.grid.entidades.*;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;
import co.edu.uniquindio.unimarket.excepciones.*;

@RunWith(Arquillian.class)
public class adminEjbTest {

	@EJB
	private AdminEJB adminEJB;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class)

				.addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	/**
	 * Metodo que permite probar el registro de un usuario
	 * 
	 * @throws ElementoRepetidoException
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void registrarUsuarioTest() throws ElementoRepetidoException {

		Usuario u = new Usuario();

		List<Favorito> favoritos = new ArrayList<Favorito>();
		u.setFavoritos(favoritos);

		u.setNumeroTelefono("3157581");

		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		u.setCalificaciones(calificaciones);

		List<Comentario> comentarios = new ArrayList<Comentario>();
		u.setComentario(comentarios);

		List<Compra> compras = new ArrayList<Compra>();
		u.setCompras(compras);

		u.setContraseña("contraseña");

		u.setDireccion("Quimbaya");

		u.setNombreCompleto("Luis felipe rincon");

		u.setCedula("1040");

		u.setRol(Rol.VENDEDOR);

		u.setEmail("erdiv@gmail.com");

		try {
			Usuario usuario = adminEJB.registrarUsuario(u);

			Assert.assertNotNull(usuario);

		} catch (ElementoRepetidoException e) {

			throw new ElementoRepetidoException(e.getMessage());
		}

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarUsuariosTest() {

		List<Usuario> usuarios;
		usuarios = adminEJB.listarUsuarios();
		Assert.assertEquals(2, usuarios.size());
		
		
		for(Usuario user:usuarios) {
			
			System.out.println(user.toString());
		}

	}

	@Test()
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "producto.json" })
	public void buscarProductoPorIdTets() throws NoExisteElementosException {

		try {
			Producto p = adminEJB.buscarProducto(1);

			Assert.assertNotNull(p);
		} catch (NoExisteElementosException e) {

			throw new NoExisteElementosException(e.getMessage());

		}

	}

}
