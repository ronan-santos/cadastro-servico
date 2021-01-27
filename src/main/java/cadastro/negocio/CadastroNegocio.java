package cadastro.negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;

import cadastro.negocio.dto.CadastroDto;
import cadastro.negocio.excecao.NegocioException;
import cadastro.negocio.excecao.enuns.MensagemEnum;
import cadastro.persistencia.dao.CadastroDao;
import cadastro.persistencia.entidade.Cadastro;

@Stateless
public class CadastroNegocio {
	
	@Inject
	private CadastroDao dao;
	
	public List<CadastroDto> obterCadastros() {
		
		return dao
				.obterTodos()
					.stream()
						.map(item -> CadastroDto.toDto(item) )
					.collect(Collectors.toList());
	}
	
	public List<CadastroDto> obterCadastrosPor(String nome, String documento){
		
		List<Cadastro> cadastros = dao.obterPorFiltro(nome, documento);
		
		if(Objects.isNull(cadastros) || cadastros.isEmpty() ) {
			throw new NegocioException(MensagemEnum.MSG002);
		}
		
		return cadastros
					.stream()
						.map(item -> CadastroDto.toDto(item))
					.collect(Collectors.toList());
	}
	
	private void obterPorDocumento(String documento) {
		
		if(dao.obterPorDocumento(documento).isPresent()) {
			
			throw  new NegocioException(MensagemEnum.MSG003);
		}

	}
	
	public void salvarCadastro(CadastroDto dto ) {
		
		validarDados(dto);
		validarNascimento(dto);
		
		if(ObjectUtils.isEmpty(dto.getId())) {
			obterPorDocumento(dto.getDocumento());
			dao.salvar(CadastroDto.toEntidade(dto));
		}else {
			
			dao.atualizar(CadastroDto.toEntidade(dto));;
		}
		
		
	}
	
	public void validarNascimento(CadastroDto dto) {
		
		if(ObjectUtils.isNotEmpty(dto.getNascimento())
			&& (dto.getNascimento().isAfter(LocalDate.now())
				 || dto.getNascimento().isEqual(LocalDate.now()))) {
			throw new NegocioException(MensagemEnum.MSG004);
		}
	}
	
	public void validarDados(CadastroDto dto) {
		
		if(ObjectUtils.isEmpty(dto.getNome())
			|| ObjectUtils.isEmpty(dto.getDocumento())
			|| ObjectUtils.isEmpty(dto.getTipoDocumento())
			|| (dto.getTipoDocumento().equals(2)
					&& (ObjectUtils.isEmpty(dto.getIdentidade())
						 || ObjectUtils.isEmpty(dto.getNascimento())
						 ))){
		
			throw new NegocioException(MensagemEnum.MSG001);
		}
	}
	
	public CadastroDto obterPor(Long id) throws NegocioException {
		
		Cadastro cadastro = dao.obterPor(id);
		
		if(Objects.isNull(cadastro)) {
			throw new NegocioException(MensagemEnum.MSG002);
		}
		
		return CadastroDto.toDto(cadastro);
	}

}
