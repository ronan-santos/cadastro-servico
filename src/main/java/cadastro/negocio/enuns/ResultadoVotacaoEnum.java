package cadastro.negocio.enuns;

import lombok.Getter;

public enum ResultadoVotacaoEnum {

	APROVADO("Pauta aprovada"),
	REPROVADO("Pauta não apovada"),
	EMPATE("Houve um empate"),
	SEM_VOTO("Não houve votos para a pauta");
	
	@Getter
	private String resultado;
	
	private ResultadoVotacaoEnum(String resultado) {
		this.resultado = resultado;
	}
	
}
