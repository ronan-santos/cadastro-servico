package cadastro.persistencia.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class Dao  {
	
	@PersistenceContext(unitName = "cadastro.postgresql.pu")
	private EntityManager em;
	
	private static final String FROM = "from ";

	public void salvar(final Object obj) {
        em.persist(obj);
        em.flush();
    }
	
	public void deletar(final Object obj) {
		em.remove(em.merge(obj));
		em.flush();
	}
	
	public void atualizar(final Object obj) {
		em.merge(obj);
		em.flush();
	}
	
	public <T> T obterPor(final Class<T> entidade, final Object id ){
		
		return em.find(entidade, id);
	}
	
	public <T> List<T> obterTodos(final Class<T> classe ){
		
		return em.createQuery(FROM.concat(classe.getSimpleName()), classe).getResultList();
		
	}

	public EntityManager getEntityManager() {
		return em;
	}
	
	
	
	

}
