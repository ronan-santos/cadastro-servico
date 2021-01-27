package cadastro.view;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cadastro.negocio.AssociadoNegocio;
import cadastro.negocio.dto.AssociadoDto;
import cadastro.negocio.excecao.NegocioException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("associados")
@Api("associados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AssociadoView {
	
	@Inject
	private AssociadoNegocio negocio;

	@GET
	@ApiOperation(value = "Retorna todos os associados cadastrados", notes = "Retorna todos os associados cadastrados",
				  response = AssociadoDto.class)
	public Response obterAssociados() throws NegocioException{
		
		
		return Response.ok(negocio.obterAssociados()).build();
		
	}
	
	@POST
	@ApiOperation(value = "Cadastra um novo associado")
	@ApiResponses(value = @ApiResponse(code = 200, message = "Usuário cadastro com sucesso"))
	public Response salvarAssociado(AssociadoDto associado ) {
		
		negocio
 		.salvarNovoAssociado(AssociadoDto.toEntidade(associado));
		
		return  Response.ok().build();
	}
	
	@PUT
	@ApiOperation(value = "Altera um associado")
	@ApiResponses(value = @ApiResponse(code = 200, message = "Usuário alterado com sucesso"))
	public Response alterarAssociado(AssociadoDto associado) {
		
		negocio.alterarDadosAssociado(AssociadoDto.toEntidade(associado));
		
		return Response.ok().build();
	}
	
	@GET
	@Path("{id}")
	@ApiOperation(value = "Retorna um associado pelo identificador", response = AssociadoDto.class)
	public Response obterAssociadoPor(@PathParam("id") Long id) {
		
		return Response.ok(AssociadoDto.toDto(negocio.obterAssociadoPor(id)) ).build();
	}
	
	@GET
	@Path("{cpf}")
	@ApiOperation(value = "Retorna um associado pelo cpf", response = AssociadoDto.class)
	public Response obterAssociadoPor(@PathParam("cpf") String cpf ) {
		
		return Response.ok(AssociadoDto.toDto(negocio.obterAssociadoPor(cpf))).build();
	}
	
	
}
