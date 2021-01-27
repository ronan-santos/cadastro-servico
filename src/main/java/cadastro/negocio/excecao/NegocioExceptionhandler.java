package cadastro.negocio.excecao;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NegocioExceptionhandler implements ExceptionMapper<NegocioException> {
	

	@Override
	public Response toResponse(NegocioException exception) {
		
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}
