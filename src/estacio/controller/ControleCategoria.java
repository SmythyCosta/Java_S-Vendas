package estacio.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import estacio.dao.CategoriaDAO;
import estacio.dao.ClienteDAO;
import estacio.models.Categoria;
import estacio.models.Cliente;

@ManagedBean(name="controleCategoria")
@SessionScoped
public class ControleCategoria implements Serializable {

	private CategoriaDAO dao;
	private Categoria objeto;
	
	
	public ControleCategoria(){
		dao = new CategoriaDAO();
	}

	
	public String listar(){
		return "/app/categorias/listar?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Categoria();
		return "form";
	}
	
	public String cancelar(){
		return "listar";
	}
	
	public String gravar(){
		if(dao.gravar(objeto)){
			return "listar";
		}else{
			return "form";
		}
	}
	
	public String alterar(Categoria obj){
		objeto = obj;
		return "form";
	}
	
	public String excluir(Categoria obj){
		dao.excluir(obj);
		return "listar";
	}

	2 	juca boleiro 	222.222.222-22 	(55) 5555-55555 	Alterar	Excluir
	1 	Vitória Shophia 	222.222.222-22 	(85) 0000-00000 	Alterar	Excluir
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
