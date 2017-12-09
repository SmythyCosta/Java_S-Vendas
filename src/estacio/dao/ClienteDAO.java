package estacio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import estacio.models.Cliente;
import estacio.models.Produto;
import estacio.util.EntityManagerUtil;
import estacio.util.UtilErros;
import estacio.util.UtilMensagens;

public class ClienteDAO{
	
	private EntityManager em;

	public ClienteDAO(){
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<Cliente> ListarTodos(){
		return em.createQuery("from Cliente order by nome").getResultList();
	}
	
	public boolean gravar(Cliente obj){
		try {
			em.getTransaction().begin();
			if (obj.getId() == null){
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Cliente inserido com sucesso!");
			return true;

		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao inserir cliente: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean excluir(Cliente obj){
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Cliente removido com sucesso!");
			return true;
			
		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover cliente: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	

	public Cliente localizar(Integer id){
		return em.find(Cliente.class, id);
	}

	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}	
	
	
}
