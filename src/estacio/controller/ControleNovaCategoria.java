package estacio.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import estacio.dao.CategoriaDAO;
import estacio.dao.ClienteDAO;
import estacio.models.Categoria;
import estacio.models.Cliente;

@ManagedBean(name="controleNovaCategoria")
@SessionScoped
public class ControleNovaCategoria implements Serializable {

	private CategoriaDAO dao;
	private Categoria objeto;
	
	
	public ControleNovaCategoria(){
		dao = new CategoriaDAO();
		
		
	}

	
	public String novo(){
		objeto = new Categoria();
		return "/app/categorias/novo?faces-redirect=true";
	}
	
	
	public String gravar(){
		if(dao.gravar(objeto)){
			return "/app/produtos/listar?faces-redirect=true";
		}else{
			return "novo";
		}
	}
	
	


	public CategoriaDAO getDao() {
		return dao;
	}


	public void setDao(CategoriaDAO dao) {
		this.dao = dao;
	}


	public Categoria getObjeto() {
		return objeto;
	}


	public void setObjeto(Categoria objeto) {
		this.objeto = objeto;
	}
	
		
}
