package co.uniquindio.beans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import org.primefaces.PrimeFaces;

@ManagedBean("menuBean")
@ApplicationScoped
public class MenuBean {
	
	  public void mostrarIniciarSesion() {
	        Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        PrimeFaces.current().dialog().openDynamic("iniciarSesion", options, null);
	    }
	
	
	
	
	

}
