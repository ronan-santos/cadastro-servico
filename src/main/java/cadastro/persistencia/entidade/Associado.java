package cadastro.persistencia.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TB01_ASSOCIADO", schema = "SICREDI")
@NamedQuery(name = "Associado.obterPorCPF", query = "select a from Associado a where a.cpf = :documento ")
public class Associado implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7167555687376218147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NU_ASSOCIADO")
	private Long id;
	
	@Size(max = 100)
	@NotEmpty
	@Column(name = "NO_ASSOCIADO")
	private String nome;
	
	@Size(max = 11, min = 11)
	@NotEmpty
	@Column(name = "COD_CPF")
	private String cpf;
}
