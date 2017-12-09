package estacio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import estacio.models.Financas;
import estacio.util.EntityManagerUtil;
import estacio.util.UtilErros;
import estacio.util.UtilMensagens;

public class FinancasDAO {

	
	private EntityManager em;

	public FinancasDAO() {
		em = EntityManagerUtil.getEntityManager();
				
	}
	
	public List<FinancasDAO> ListarTodos(){
		return em.createQuery("from Financas order by nome").getResultList();
	}
	
	public boolean gravar(Financas obj){
		try {
			em.getTransaction().begin();
			if (obj.getId() == null){
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Financas inserido com sucesso!");
			return true;

		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao inserir Financas: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean excluir(Financas obj){
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Financas removido com sucesso!");
			return true;
			
		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover Financas: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	

	public Financas localizar(Integer id){
		return em.find(Financas.class, id);
	}

	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}	
	
}
