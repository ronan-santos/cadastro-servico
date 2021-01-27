package cadastro.negocio.excecao;


import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import cadastro.negocio.excecao.enuns.MensagemEnum;



public class ResoucebundleMensagens {

	private static Locale LOCALE = new Locale("pt", "BR");
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("mensagens", LOCALE);
;
	
	protected static String obterMensagemPor(String chave) {
		
		return Objects.nonNull(chave) &&  BUNDLE.containsKey(chave) ? BUNDLE.getString(chave)
																	: BUNDLE.getString(MensagemEnum.MSG001.name());
	}

}
