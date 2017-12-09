package estacio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import estacio.models.Categoria;
import estacio.util.EntityManagerUtil;
import estacio.util.UtilErros;
import estacio.util.UtilMensagens;

public class CategoriaDAO{
	
	
	private EntityManager em;

	
	public CategoriaDAO(){
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<Categoria> ListarTodos(){
		return em.createQuery("from Categoria order by descricao").getResultList();
	}
	
	public boolean gravar(Categoria obj){
		try {
			em.getTransaction().begin();
			if (obj.getId() == null){
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Categoria inserida com sucesso!");
			return true;

		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao inserir categoria: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean excluir(Categoria obj){
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Categoria removida com sucesso!");
			return true;
			
		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover categoria: "+
												UtilErros.getMensagemErro(e));
			return false;
		}
	}
	

	public Categoria localizar(Integer id){
		return em.find(Categoria.class, id);
	}

	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
}
