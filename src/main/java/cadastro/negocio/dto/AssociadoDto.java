package cadastro.negocio.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cadastro.persistencia.entidade.Associado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssociadoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2249439201104719708L;
	private Long id;
	private String nome;
	private String cpf;
	
	
	public static AssociadoDto toDto(Associado associado ) {
		
		return Objects.nonNull(associado)
					?	AssociadoDto
							.builder()
								.id(associado.getId())
								.nome(associado.getNome())
								.cpf(associado.getCpf())
							.build()
					: AssociadoDto.builder().build();
		
	}
	
	public static Associado toEntidade(AssociadoDto dto ) {
		
		return Objects.nonNull(dto)
					? Associado
							.builder()
								.id(dto.getId())
								.nome(dto.getNome())
								.cpf(dto.getCpf())
							.build()
					: Associado.builder().build();
	}

}
