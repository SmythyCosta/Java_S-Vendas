package estacio.testes;

import javax.persistence.EntityManager;

import estacio.models.Cliente;
import estacio.util.EntityManagerUtil;

public class TesteInserirClientes{
	
	public static void main(String[] args) {
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		Cliente c = new Cliente();
		
		c.setNome("Emanoel");
		c.setCpf("123456666");
		c.setTelefone("66389899444");
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}

}
