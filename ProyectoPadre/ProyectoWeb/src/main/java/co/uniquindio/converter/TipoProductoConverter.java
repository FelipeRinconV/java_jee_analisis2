package co.uniquindio.converter;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import co.edu.uniquindio.grid.entidades.Categoria;
import co.edu.uniquindio.unimarket.ejb.AdminEJB;

@Named("tipoProductoConverter")
@ApplicationScoped
public class TipoProductoConverter implements Converter<Categoria> {

	@EJB
	private AdminEJB adminEJB;

	@Override
	public Categoria getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null && value.length() > 0) {

			try {

				Categoria cat = adminEJB.darCategoriaPorId(Integer.parseInt(value));

				return cat;

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Categoria value) {

		if (value != null) {

			return String.valueOf(value.getId());

		}

		return null;
	}

}
