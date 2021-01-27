package cadastro.negocio;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import cadastro.negocio.dto.AssociadoDto;
import cadastro.negocio.excecao.NegocioException;
import cadastro.negocio.excecao.enuns.MensagemEnum;
import cadastro.persistencia.dao.AssociadoDao;
import cadastro.persistencia.entidade.Associado;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AssociadoNegocio {

	@Inject
	private AssociadoDao dao;

	public List<AssociadoDto> obterAssociados() throws NegocioException{

		return dao
				.obterTodos()
					.stream()
						.map(item -> AssociadoDto.toDto(item) )
						.collect(Collectors.toList());
	

	}
	
	public void salvarNovoAssociado(Associado associado ) {
		
		validarInclusaoAssociado(associado);
		dao.salvar(associado);
	}
	
	public Associado obterAssociadoPor(String cpf) {
	//	verificadorCpfAssociado.obterHabilitacoVotoPor(cpf);
		return dao.obterAssociadoPor(cpf).orElseThrow(() ->  new NegocioException(MensagemEnum.MSG002)); 
	}
	
	public void alterarDadosAssociado(Associado associado) {
		
		validarAlteracaoAssociado(associado);
		dao.atualizar(associado);
	}
	
	
	public Associado obterAssociadoPor(Long id) {
		Associado associado = dao.obterPor(id);
		
		if(Objects.isNull(associado))
			throw new NegocioException(MensagemEnum.MSG002);
		
		return associado;
	}
	
	private void validarInclusaoAssociado(Associado associado ) {
		
		if(Objects.isNull(associado.getNome()) || StringUtils.isBlank(associado.getNome()))
			throw new NegocioException(MensagemEnum.MSG003);
		
		if(Objects.isNull(associado.getCpf()) || StringUtils.isBlank(associado.getCpf()))
			throw new NegocioException(MensagemEnum.MSG004);
		
		if(Objects.nonNull(dao.obterAssociadoPor(associado.getCpf()).orElse(null)))
			throw new NegocioException(MensagemEnum.MSG005);
	}
	
	private void validarAlteracaoAssociado(Associado associado ) {
		
		Associado associadoExistente = null;
		
		if(Objects.isNull(associado.getId()))
			throw new NegocioException(MensagemEnum.MSG007);
		
		associadoExistente = dao.obterPor(associado.getId());
		
		if(Objects.isNull(associadoExistente))
			throw new NegocioException(MensagemEnum.MSG002);
		
		if(Objects.isNull(associado.getCpf()) || StringUtils.isBlank(associado.getCpf()) 
				|| !associado.getCpf().trim().equalsIgnoreCase(associadoExistente.getCpf().trim()))
			throw new NegocioException(MensagemEnum.MSG006);
	}
	
	
	
	
	
}
