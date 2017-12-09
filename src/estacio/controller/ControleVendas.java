package estacio.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import estacio.conversores.ConverterCliente;
import estacio.dao.ClienteDAO;
import estacio.dao.VendasDAO;
import estacio.models.Vendas;

@ManagedBean(name = "controleVendas")
@SessionScoped
public class ControleVendas implements Serializable {

	private VendasDAO dao;
	private Vendas objeto;
	
	private ClienteDAO daoCliente;
	private ConverterCliente converterCliente;

	public ControleVendas() {
		dao = new VendasDAO();
		daoCliente = new ClienteDAO();
		converterCliente = new ConverterCliente();
	}

	public String listar() {
		return "/app/vendas/listar?faces-redirect=true";
	}

	public String novo() {
		objeto = new Vendas();
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

	public String alterar(Vendas obj) {
		objeto = obj;
		return "form";
	}

	public String excluir(Vendas obj) {
		dao.excluir(obj);
		return "listar";
	}

	public VendasDAO getDao() {
		return dao;
	}

	public void setDao(VendasDAO dao) {
		this.dao = dao;
	}

	public Vendas getObjeto() {
		return objeto;
	}

	public void setObjeto(Vendas objeto) {
		this.objeto = objeto;
	}

	public ClienteDAO getDaoCliente() {
		return daoCliente;
	}

	public void setDaoCliente(ClienteDAO daoCliente) {
		this.daoCliente = daoCliente;
	}

	public ConverterCliente getConverterCliente() {
		return converterCliente;
	}

	public void setConverterCliente(ConverterCliente converterCliente) {
		this.converterCliente = converterCliente;
	}

	
}

