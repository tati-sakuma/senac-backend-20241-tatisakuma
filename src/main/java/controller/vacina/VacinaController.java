package controller.vacina;

import exception.vacina.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.vacina.Vacina;
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

}

