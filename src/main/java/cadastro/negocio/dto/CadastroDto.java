package cadastro.negocio.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import cadastro.negocio.dto.AssociadoDto.AssociadoDtoBuilder;
import cadastro.persistencia.entidade.Cadastro;
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
public class CadastroDto implements Serializable {
	
	/**
	 * serialVersionUID - long.
	 */
	private static final long serialVersionUID = 8424259391996109947L;
	private Long id;
	private String nome;
	private String documento;
	private String identidade;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate nascimento;
	private Integer tipoDocumento;
	
	public static CadastroDto toDto(Cadastro cadastro) {
		
		return Objects.nonNull(cadastro)
				? CadastroDto
					.builder()
						.id(cadastro.getId())
						.nome(cadastro.getNome())
						.documento(cadastro.getDocumento())
						.identidade(cadastro.getIdentidade())
						.tipoDocumento(cadastro.getTipoDocumento())
						.nascimento(cadastro.getNascimento())
					.build()
				: CadastroDto.builder().build();
		
	}
	
	public static Cadastro toEntidade(CadastroDto dto ) {
		
		return Objects.nonNull(dto)
				? Cadastro
					.builder()
						.id(dto.getId())
						.nome(dto.getNome())
						.documento(dto.getDocumento())
						.identidade(dto.getIdentidade())
						.tipoDocumento(dto.getTipoDocumento())
						.nascimento(dto.getNascimento())
					.build()
				: Cadastro.builder().build();
	}

}
