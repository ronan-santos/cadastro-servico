package cadastro.persistencia.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import cadastro.persistencia.entidade.Cadastro;

@RequestScoped
public class CadastroDao extends AbstractDao<Cadastro, Long> {
	
	public CadastroDao() {
		super(Cadastro.class);
	}
	
	public Optional<Cadastro> obterPorDocumento(String documento){
		
		TypedQuery<Cadastro> tq = dao.getEntityManager().createNamedQuery("Cadastro.obterPorCpf", Cadastro.class);
		
		tq.setParameter("documento", documento);
		
		return tq.getResultStream().findAny();
	}
	
	public List<Cadastro> obterTodos(){
		return dao.obterTodos(Cadastro.class);
	}
	
	public List<Cadastro> obterPorFiltro(String nome, String documento){
		
		String query = obterQueryConsultaCadastro(nome, documento);
		
		TypedQuery<Cadastro> queryCadastro = dao.getEntityManager()
													.createQuery(obterQueryConsultaCadastro(nome, documento), Cadastro.class);
		
		setParametrosQueryConsultaCadastro(queryCadastro, nome, documento);
		
		return queryCadastro.getResultList();

	}
	
	private void setParametrosQueryConsultaCadastro(TypedQuery<Cadastro> query, String nome, String documento) {
		
		if(StringUtils.isNotBlank(nome)) {
			query.setParameter("nome", "%"+nome+"%");
		}
		
		if(StringUtils.isNotBlank(documento)) {
			query.setParameter("documento", documento);
		}
	}
	
	private String obterQueryConsultaCadastro(String nome, String documento) {
		
		StringBuilder query = new StringBuilder("select c from Cadastro c where ");
		
		 if(StringUtils.isNotBlank(nome) && StringUtils.isNotBlank(documento) ) {
			query.append(" c.nome like  :nome  ");
			query.append(" and c.documento= :documento ");
		}else {
			
			if(StringUtils.isNotBlank(nome)) {
				query.append(" c.nome like :nome  ");
			}else if(StringUtils.isNotBlank(documento)) {
				query.append(" c.documento= :documento ");
			}
		}
		 
		 return query.toString();
	}
	

}
