package estacio.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import estacio.dao.ClienteDAO;
import estacio.models.Cliente;

@ManagedBean(name="controleCliente")
@SessionScoped
public class ControleCliente implements Serializable {

	private ClienteDAO dao;
	private Cliente objeto;
	
	
	public ControleCliente(){
		dao = new ClienteDAO();
	}

	
	public String listar(){
		return "/app/clientes/listar?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Cliente();
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
	
	public String alterar(Cliente obj){
		objeto = obj;
		return "form";
	}
	
	public String excluir(Cliente obj){
		dao.excluir(obj);
		return "listar";
	}
	
	/*
	 * Getrs e Setrs
	 * */
	public ClienteDAO getDao() {
		return dao;
	}

	public void setDao(ClienteDAO dao) {
		this.dao = dao;
	}

	public Cliente getObjeto() {
		return objeto;
	}

	public void setObjeto(Cliente objeto) {
		this.objeto = objeto;
	}


	
	
	
	
	
}
