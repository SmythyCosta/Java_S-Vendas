package estacio.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import estacio.models.Produto;
import estacio.util.EntityManagerUtil;

public class ConverterProduto implements Converter, Serializable{
	
	// converte da tela para o objeto
			@Override
			public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
				if (string == null || string.equals("Selecione um Produto")){
					return null;
				}
				return EntityManagerUtil.getEntityManager().find(Produto.class, 
						Integer.parseInt(string));
			}
			
			// converte do objeto para a tela
			@Override
			public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
				if (o == null){
					return null;
				}
				Produto obj = (Produto) o;
				return obj.getId().toString();
			}
		

}
