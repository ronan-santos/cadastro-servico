package cadastro.view;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import cadastro.negocio.CadastroNegocio;
import cadastro.negocio.dto.CadastroDto;
import cadastro.negocio.excecao.NegocioException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("cadastro")
@Api("cadastro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroView {
	
	@Inject
	private CadastroNegocio negocio;
	
	@GET
	@ApiOperation(value = "Retorna todos os cadastros da base", notes = "Retorna todos os cadastros da base",
				 response = CadastroDto.class)
	public Response obterTodos() {
		return Response.ok(negocio.obterCadastros()).build();
	}
	
	@POST
	@ApiOperation(value = "Inclui um novo cadastro na base", notes = "Inclui um novo cadastro na base",
	 response = CadastroDto.class)
	public Response salvarCadastro(CadastroDto novoCadastro ) {
		
		negocio.salvarCadastro(novoCadastro);
		
		return Response.ok().build();
	}
	
	@GET()
	@Path("{id}")
	@ApiOperation(value = "Consulta um cadastro na base pelo id", notes = "Consulta um cadastro na base pelo id",
	 response = CadastroDto.class)
	public Response obterCadastroPor( @PathParam("id")  Long id) {
		try {
			return Response.ok(negocio.obterPor(id)).build();
		}catch (NegocioException e) {
			
			return Response
					.status(Status.NOT_FOUND)
					.entity(e.getMessage())
					.
					build();
		}
		
	}
	
	@GET()
	@Path("pesquisa")
	@ApiOperation(value = "Consulta cadastro na base por filtros", notes = "Consulta cadastro na base por filtros",
	 response = CadastroDto.class)
	public Response consultarCadastros(@QueryParam("nome") String nome, @QueryParam("documento") String documento ) {
		
		return Response.ok(negocio.obterCadastrosPor(nome, documento)).build();
	}
}
