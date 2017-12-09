package estacio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import estacio.models.Produto;
import estacio.util.EntityManagerUtil;
import estacio.util.UtilErros;
import estacio.util.UtilMensagens;

public class ProdutoDAO {

	private EntityManager em;

	public ProdutoDAO() {
		em = EntityManagerUtil.getEntityManager();
	}

	public List<Produto> ListarTodos() {
		return em.createQuery("from Produto order by nome").getResultList();
	}

	public boolean gravar(Produto obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Produto inserido com sucesso!");
			return true;

		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao inserir produto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	public boolean excluir(Produto obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Produto removido com sucesso!");
			return true;

		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover produto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	public Produto localizar(Integer id) {
		return em.find(Produto.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
