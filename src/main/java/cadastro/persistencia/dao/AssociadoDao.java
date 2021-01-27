package cadastro.persistencia.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import cadastro.persistencia.entidade.Associado;

@RequestScoped
public class AssociadoDao extends AbstractDao<Associado, Long> {

	public AssociadoDao() {
		
		super(Associado.class);
	}
	
	public List<Associado> obterTodos(){
		
		return dao.obterTodos(Associado.class);
	}
	
	public Optional<Associado> obterAssociadoPor(String cpf) {
		
		TypedQuery<Associado> tq = dao.getEntityManager().createNamedQuery("Associado.obterPorCPF", Associado.class);
		tq.setParameter("documento", cpf);
		
		return tq.getResultStream().findFirst();	}

}
