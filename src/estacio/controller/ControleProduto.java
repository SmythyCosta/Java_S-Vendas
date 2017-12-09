package estacio.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import estacio.conversores.ConverterCategoria;
import estacio.dao.CategoriaDAO;
import estacio.dao.ProdutoDAO;
import estacio.models.Produto;

import org.primefaces.event.ToggleEvent;

@ManagedBean(name = "controleProduto")
@SessionScoped
public class ControleProduto implements Serializable {

	//dados para redrizacao
	private Boolean novaCategoria;
	private Boolean opcaoSelecionada;
	
	
	private ProdutoDAO dao;
	private Produto objeto;
	
	private CategoriaDAO daoCategoria;
	private ConverterCategoria converterCategoria;

	public ControleProduto() {
		
		opcaoSelecionada = false;
		
		dao = new ProdutoDAO();
		daoCategoria = new CategoriaDAO();
		converterCategoria = new ConverterCategoria();
	}
	
	/* 
	* este metodo vai capturar o evento selecionado pelo usuario no 
	* radio buton
	*/	
	public void tipoChange(ValueChangeEvent e){
		novaCategoria = (Boolean) e.getNewValue();		
		selecionar();
	}
	
	/* 
	* este metodo vai selecionar o que o usuario fez
	*/
	public String selecionar(){
		opcaoSelecionada = true;
		String selecao = novaCategoria == true ? "Pessoa Fisica" : "Pessoa Juridica";
		FacesMessage msg = new FacesMessage("Tipo seleciona: " +selecao);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "form";
	}
	
	
	//Fieldset  do prime faces
	public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Toggled", "Visibility:" + event.getVisibility());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	

	public String listar(){
		return "/app/produtos/listar?faces-redirect=true";
	}

	public String novo() {
		objeto = new Produto();
		return "form";
	}

	public String cancelar() {
		return "listar";
	}

	public String gravar() {
		if (dao.gravar(objeto)) {
			return "listar";
		} else {
			return "form";
		}
	}

	public String alterar(Produto obj) {
		objeto = obj;
		return "form";
	}

	public String excluir(Produto obj) {
		dao.excluir(obj);
		return "listar";
	}

	public ProdutoDAO getDao() {
		return dao;
	}

	public void setDao(ProdutoDAO dao) {
		this.dao = dao;
	}

	public Produto getObjeto() {
		return objeto;
	}

	public void setObjeto(Produto objeto) {
		this.objeto = objeto;
	}

	public CategoriaDAO getDaoCategoria() {
		return daoCategoria;
	}

	public void setDaoCategoria(CategoriaDAO daoCategoria) {
		this.daoCategoria = daoCategoria;
	}

	public ConverterCategoria getConverterCategoria() {
		return converterCategoria;
	}

	public void setConverterCategoria(ConverterCategoria converterCategoria) {
		this.converterCategoria = converterCategoria;
	}

	public Boolean getNovaCategoria() {
		return novaCategoria;
	}

	public void setNovaCategoria(Boolean novaCategoria) {
		this.novaCategoria = novaCategoria;
	}

	public Boolean getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(Boolean opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}
	
	
	
	
	
	

}
