package controller.vacina;

import java.util.ArrayList;

import exception.vacina.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.vacina.Pais;
import service.vacina.PaisService;

@Path("/pais")
public class PaisController {
	private PaisService service = new PaisService();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pais salvar (Pais novoPais) throws ControleVacinasException {
		return service.salvar(novoPais);
	}

	@GET
	@Path("/todos")
	public ArrayList<Pais> consultarTodas(){
		 return service.consultarTodos();
	}
	
	@DELETE
	@Path("excluir/{id}")
	public boolean excluir(@PathParam("id") int id) throws ControleVacinasException {
		return service.excluir(id);
	}
}
