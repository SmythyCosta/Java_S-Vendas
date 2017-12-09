package estacio.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import estacio.models.Categoria;
import estacio.util.EntityManagerUtil;

public class ConverterCategoria implements Converter, Serializable{

	// converte da tela para o objeto
		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
			if (string == null || string.equals("Selecione uma categoria")){
				return null;
			}
			return EntityManagerUtil.getEntityManager().find(Categoria.class, 
					Integer.parseInt(string));
		}
		
		// converte do objeto para a tela
		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
			if (o == null){
				return null;
			}
			Categoria obj = (Categoria) o;
			return obj.getId().toString();
		}
	
}
