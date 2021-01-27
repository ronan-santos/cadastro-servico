package cadastro.persistencia.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cadastro.persistencia.entidade.Associado.AssociadoBuilder;
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
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb04_cadastro", schema = "cadastro")
@NamedQuery(name = "Cadastro.obterPorCpf",query = "select c from Cadastro c where c.documento = :documento ")
public class Cadastro implements Serializable {
	
	/**
	 * serialVersionUID - long.
	 */
	private static final long serialVersionUID = -1668431403203974265L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "nascimento")
	private LocalDate nascimento;
	
	@Column(name = "documento")
	private String documento;
	
	@Column(name = "identidade")
	private String identidade;
	
	@Column(name = "tp_pessoa")
	private Integer tipoDocumento;
	
	@OneToMany(mappedBy = "cadastro")
	private List<Telefone> telefones;

}
