package estacio.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import estacio.models.Cliente;
import estacio.util.EntityManagerUtil;

public class ConverterCliente implements Converter, Serializable {

	// converte da tela para o objeto
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione um cliente")) {
			return null;
		}
		return EntityManagerUtil.getEntityManager().find(Cliente.class, Integer.parseInt(string));
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		Cliente obj = (Cliente) o;
		return obj.getId().toString();
	}
}
