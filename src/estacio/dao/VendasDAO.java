package estacio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import estacio.models.Vendas;
import estacio.util.EntityManagerUtil;
import estacio.util.UtilErros;
import estacio.util.UtilMensagens;

public class VendasDAO{
	
	
	private EntityManager em;

	
	public VendasDAO(){
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<Vendas> ListarTodos(){
		return em.createQuery("from Vendas order by data").getResultList();
	}
	
	public boolean gravar(Vendas obj){
		try {
			em.getTransaction().begin();
			if (obj.getId() == null){
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Venda registrada com sucesso!");
			return true;

		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao registrar a venda: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean excluir(Vendas obj){
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Venda removida com sucesso!");
			return true;
			
		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover venda: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	

	public Vendas localizar(Integer id){
		return em.find(Vendas.class, id);
	}

	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
}
