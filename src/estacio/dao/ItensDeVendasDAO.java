package estacio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import estacio.models.ItensDeVenda;
import estacio.util.EntityManagerUtil;
import estacio.util.UtilErros;
import estacio.util.UtilMensagens;

public class ItensDeVendasDAO{
	
	
	private EntityManager em;

	
	public ItensDeVendasDAO(){
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<ItensDeVenda> ListarTodos(){
		return em.createQuery("from ItensDeVenda order by valor").getResultList();
	}
	
	public boolean gravar(ItensDeVenda obj){
		try {
			em.getTransaction().begin();
			if (obj.getId() == null){
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Itens de Venda registrado com sucesso!");
			return true;

		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao registrar os Itens de Venda: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean excluir(ItensDeVenda obj){
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Itens de Venda removido com sucesso!");
			return true;
			
		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover os Itens de Venda: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	

	public ItensDeVenda localizar(Integer id){
		return em.find(ItensDeVenda.class, id);
	}

	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
}
