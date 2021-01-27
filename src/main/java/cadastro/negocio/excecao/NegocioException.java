package cadastro.negocio.excecao;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

import cadastro.negocio.excecao.enuns.MensagemEnum;

public class NegocioException extends RuntimeException implements Serializable {


	private static final long serialVersionUID = 1L;

	
	public NegocioException() {
		super();
	}
	
	public NegocioException(MensagemEnum msg, Status status) {
		
		super(ResoucebundleMensagens.obterMensagemPor(msg.name()));
		
	}
	
	public NegocioException(MensagemEnum msg) {
		
		super(ResoucebundleMensagens.obterMensagemPor(msg.name()));
		
	}

}
