package cadastro.persistencia.dao;

import java.util.List;

import javax.inject.Inject;

public abstract class AbstractDao<T, ID> {

	private final Class<T> classe;
	
	@Inject
	protected Dao dao;
	
	public AbstractDao(Class<T> classe) {
		this.classe = classe;
	}
	
	public T obterPor(ID id) {
		
		return dao.obterPor(classe, id);
	}
	
	public void salvar(T objeto) {
		dao.salvar(objeto);
	}
	
	public void atualizar(T objeto) {
		dao.atualizar(objeto);
	}
	
	public List<T> obterTodos(){
		return dao.obterTodos(classe);
	}
}
