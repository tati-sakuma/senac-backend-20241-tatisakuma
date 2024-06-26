package controller.vacina;

import java.util.List;

import exception.vacina.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.vacina.Vacina;
import model.seletor.vacina.VacinaSeletor;
import service.vacina.VacinaService;

@Path("/vacina")
public class VacinaController {
	private VacinaService service = new VacinaService();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina salvar(Vacina novaVacina) throws ControleVacinasException {
		return service.salvar(novaVacina);
	}
	
	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean atualizarUsuarioController(Vacina vacina) {
		return service.alterar(vacina);
	}
	
	@DELETE
	@Path("excluir/{id}")
	public boolean excluir(@PathParam("id") int id) throws ControleVacinasException {
		return service.excluir(id);
	}
	
	@GET
	@Path("consultar/{id}")
	public Vacina consultarPorId(@PathParam("id") int id) throws ControleVacinasException {
		return service.consultarPorId(id);
	}
	
	@GET
	@Path("/todas")
	public List<Vacina> consultarTodas(){
		 return service.consultarTodas();
	}
	
	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vacina> consultarComFiltro(VacinaSeletor seletor) {
		return service.consultarComFiltro(seletor);
	}
	
	@POST
	@Path("/contar")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarTotalRegistros(VacinaSeletor seletor) {
		return this.service.contarTotalRegistros(seletor);
	}
	
	
	@POST
	@Path("/total-paginas")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarPaginas(VacinaSeletor seletor) {
		return this.service.contarPaginas(seletor);
	}
	
}

