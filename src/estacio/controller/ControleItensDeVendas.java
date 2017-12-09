package estacio.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import estacio.conversores.ConverterProduto;
import estacio.conversores.ConverterVendas;
import estacio.dao.ItensDeVendasDAO;
import estacio.dao.ProdutoDAO;
import estacio.dao.VendasDAO;
import estacio.models.ItensDeVenda;

@ManagedBean(name = "controleitensDeVendas")
@SessionScoped
public class ControleItensDeVendas implements Serializable {

	// dados para redrizacao
	private Boolean novaCategoria;
	private Boolean opcaoSelecionada;

	private ItensDeVendasDAO dao;
	private ItensDeVenda objeto;

	private VendasDAO daoVendas;
	private ConverterVendas converterVendas;
	
	private ProdutoDAO daoProduto;
	private ConverterProduto converterProduto;

	public ControleItensDeVendas() {

		dao = new ItensDeVendasDAO();
		
		daoVendas = new VendasDAO();
		converterVendas = new ConverterVendas();
		
		daoProduto = new ProdutoDAO();
		converterProduto = new ConverterProduto();
	}

	public String listar() {
		return "/app/itens-vendas/listar?faces-redirect=true";
	}

	public String novo() {
		objeto = new ItensDeVenda();
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

	public String alterar(ItensDeVenda obj) {
		objeto = obj;
		return "form";
	}

	public String excluir(ItensDeVenda obj) {
		dao.excluir(obj);
		return "listar";
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

	public ItensDeVendasDAO getDao() {
		return dao;
	}

	public void setDao(ItensDeVendasDAO dao) {
		this.dao = dao;
	}

	public ItensDeVenda getObjeto() {
		return objeto;
	}

	public void setObjeto(ItensDeVenda objeto) {
		this.objeto = objeto;
	}

	public VendasDAO getDaoVendas() {
		return daoVendas;
	}

	public void setDaoVendas(VendasDAO daoVendas) {
		this.daoVendas = daoVendas;
	}

	public ConverterVendas getConverterVendas() {
		return converterVendas;
	}

	public void setConverterVendas(ConverterVendas converterVendas) {
		this.converterVendas = converterVendas;
	}

	public ProdutoDAO getDaoProduto() {
		return daoProduto;
	}

	public void setDaoProduto(ProdutoDAO daoProduto) {
		this.daoProduto = daoProduto;
	}

	public ConverterProduto getConverterProduto() {
		return converterProduto;
	}

	public void setConverterProduto(ConverterProduto converterProduto) {
		this.converterProduto = converterProduto;
	}

}
