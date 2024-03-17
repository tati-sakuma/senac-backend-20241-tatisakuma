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
import service.vacina.VacinacaoService;

@Path("/vacinacao")
public class VacinacaoController {
	private VacinacaoService service = new VacinacaoService();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacinacao salvar(Vacinacao novaVacinacao) throws ControleVacinasException {
		return service.salvar(novaVacinacao);
	}
	
	@DELETE
	@Path("excluir/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return service.excluir(id);
	}
	
	@GET
	@Path("consultar/{id}")
	public Vacinacao consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}
	
	@GET
	@Path("/todas")
	public List<Vacinacao> consultarTodas(){
		 return service.consultarTodos();
	}
}
