package controller.vacina;

import java.util.List;

import exception.vacina.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacinacao;
import service.vacina.PessoaService;

@Path("/pessoa")
public class PessoaController {

	private PessoaService service = new PessoaService();

	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException {
		return service.salvar(novaPessoa);
	}

	@DELETE
	@Path("excluir/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return service.excluir(id);
	}
	

	@GET
	@Path("consultar/{id}")
	public Pessoa consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}
	
	@GET
	@Path("/todas")
	public List<Pessoa> consultarTodas(){
		 return service.consultarTodas();
	}
	
	@GET
	@Path("/vacinacoes/{id}")
	public List<Vacinacao> consultarVacinacoes(@PathParam("id") int id){
		return service.consultarVacinacoes(id);
	}
	
	
}