package controller.vacina;

import exception.vacina.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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

}
