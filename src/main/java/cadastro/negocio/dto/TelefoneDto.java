package cadastro.negocio.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cadastro.negocio.dto.CadastroDto.CadastroDtoBuilder;
import cadastro.persistencia.entidade.Telefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * // TODO describe_this
 *
 * @author Tivit
 * @since 26/01/2021
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelefoneDto implements Serializable {
	
	/**
	 * serialVersionUID - long.
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String telefone;
	@JsonIgnore
	private CadastroDto cadastro;
	
	
	public static TelefoneDto toDto(Telefone telefone ) {
		
		return Objects.nonNull(telefone)
				? TelefoneDto
					.builder()
						.id(telefone.getId())
						.telefone(telefone.getTelefone())
					.build()
				: TelefoneDto.builder().build();
	}
	
	public static Telefone toEntidade(TelefoneDto dto) {
		
		return Objects.nonNull(dto)
				? Telefone
					.builder()
						.id(dto.getId())
						.telefone(dto.getTelefone())
						.cadastro(CadastroDto.toEntidade(dto.getCadastro()))
					.build()
				: Telefone.builder().build();
	}

}
